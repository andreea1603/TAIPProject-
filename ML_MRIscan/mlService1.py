import os

import numpy as np
import tensorflow as tf
import base64


def get_result(base64Img):
    model_path = "./checkpoint/best_model"

    final_model = tf.keras.models.load_model(model_path)

    label_to_text = {0: 'Mild_Demented', 1: 'Moderate_Demented', 2: 'Non_Demented', 3: 'Very_Mild_Demented'}

    with open("imageToSave.jpg", "wb") as fh:
        fh.write(base64.decodebytes(bytes(base64Img, 'utf-8')))

    img = tf.keras.utils.load_img(
        'MRI_of_Human_Brain.jpg', target_size=(48, 48)
    )

    img_array = tf.keras.utils.img_to_array(img)
    img_array = tf.expand_dims(img_array, 0)

    predictions = final_model.predict(img_array)
    score = tf.nn.softmax(predictions[0])

    result = {label_to_text[np.argsort(score)[0]]: float(np.sort(score)[0]),
              label_to_text[np.argsort(score)[1]]: float(np.sort(score)[1]),
              label_to_text[np.argsort(score)[2]]: float(np.sort(score)[2]),
              label_to_text[np.argsort(score)[3]]: float(np.sort(score)[3]),
              }
    return result
