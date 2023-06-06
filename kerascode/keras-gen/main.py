import pika
import multiprocessing
import json
import psycopg2
import time
# from keras_cv.models import StableDiffusion
# from PIL import Image

from MessageDto import MessageDto

def update_db(id, status):
    CONNECTION = psycopg2.connect(
        host="db",
        port="5432",
        database="label",
        user="admin",
        password="root"
    )
    cursor = CONNECTION.cursor()

    sql_update_query = """UPDATE generate.message SET progress=%s WHERE id=%s"""
    cursor.execute(sql_update_query, (status, id))
    CONNECTION.commit()
    print("Status: ", status)


def set_image_path(id, image_name):
    CONNECTION = psycopg2.connect(
        host="db",
        port="5432",
        database="label",
        user="admin",
        password="root"
    )
    cursor = CONNECTION.cursor()

    sql_update_query = """INSERT INTO generate.picture (id, message_id, content_type, name, file_path) VALUES (
    DEFAULT, %s, 'png', null, %s)"""
    cursor.execute(sql_update_query, (id, image_name))
    CONNECTION.commit()

#     Скрипт для генерации изобоажений лежит отдельно в этом же репозитории.
#     Для демонстрации работы приложения вместо генерации используется sleep 15 секунд.
def generate_image(dto):
    # Используем модель для генерации изображения
    # model = StableDiffusion(img_height=512, img_width=512, jit_compile=True)
    # img = model.text_to_image(
    #     prompt=dto.get_message(),
    #     batch_size=dto.get_batch_size(),  # How many images to generate at once
    #     num_steps=dto.get_num_steps(),  # Number of iterations (controls image quality)
    #     seed=dto.get_seed(),  # Set this to always get the same image from the same prompt
    # )
    # image_name = 'images/' + dto.get_id() + '.png'
    # Image.fromarray(img[0]).save(image_name)

    time.sleep(15)
    image_name = 'images/' + dto.get_id() + '.png'
    set_image_path(dto.get_id(), image_name)
    print("saved at ", image_name)


# Функция для обработки сообщений из очереди
def process_message(channel, method, properties, message):
    json_str = json.loads(message)
    dto = MessageDto(
        json_str.get('message'),
        json_str.get('id'),
        json_str.get('batch_size'),
        json_str.get('num_steps'),
        json_str.get('seed')
    )
    update_db(dto.get_id(), "In progress")
    generate_image(dto)
    update_db(dto.get_id(), "Finished")
    print("My process id is:", multiprocessing.current_process().pid)


# Функция для создания процесса, который будет слушать очередь
def listen_to_queue():
    param = pika.ConnectionParameters(
        host='lechat-myrabbit-1',
        port=5672,
        credentials=pika.PlainCredentials('guest', 'guest')
    )
    while True:
        try:
            print('Trying to connect: ')
            connection = pika.BlockingConnection(param)
            break
        except pika.exceptions.AMQPConnectionError:
            print('RabbitMQ is not available yet. Waiting...')
            time.sleep(5)
    print('Connection successful!')
    channel = connection.channel()
    channel.queue_declare(queue='requestQueue', durable=True)
    channel.basic_consume(queue='requestQueue', on_message_callback=process_message, auto_ack=True)
    channel.start_consuming()


if __name__ == "__main__":
    # Создание нескольких процессов для обработки сообщений из очереди
    processes = []
    for i in range(3):
        p = multiprocessing.Process(target=listen_to_queue)
        processes.append(p)
        p.start()

    # Ожидание завершения всех процессов
    for p in processes:
        p.join()

