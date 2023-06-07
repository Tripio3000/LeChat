from keras_cv.models import StableDiffusion
from PIL import Image
import uuid

# Используем модель для генерации изображения
print("Enter prompt: ")
input = str(input())
model = StableDiffusion(img_height=512, img_width=512, jit_compile=True)
img = model.text_to_image(
    prompt=input,
    batch_size=1,  # How many images to generate at once
    num_steps=25  # Number of iterations (controls image quality)
)

image_name = 'images/' + str(uuid.uuid4()) + '.png'
Image.fromarray(img[0]).save(image_name)
print("saved at ", image_name)