FROM python:3.10

RUN mkdir -p /usr/src/app/images
WORKDIR /usr/src/app

COPY . /usr/src/app
RUN pip install pika pillow psycopg2 tensorflow keras-cv

EXPOSE 5001

CMD [ "python","-u", "main.py"]