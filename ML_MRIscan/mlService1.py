import os

import numpy as np
import tensorflow as tf


def get_result(filename):
    file_name = 'best_model'
    checkpoint_path = os.path.join('checkpoint', file_name)

    final_model = tf.keras.models.load_model(checkpoint_path)

    label_to_text = {0: 'Mild_Demented', 1: 'Moderate_Demented', 2: 'Non_Demented', 3: 'Very_Mild_Demented'}
    # filename = r'MRI_of_Human_Brain.jpg'

    img = tf.keras.utils.load_img(
        filename, target_size=(48, 48)
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
