import pickle

import pandas as pd
from flask_cors import CORS
from flask import Flask, request, jsonify

b_Translate = {0: "Normal Cognitive", 1: "Subject Memory Concerns", 2: "Early Mild Cognitive Impairment",
               3: "Late Mild Cognitive Impairment", 4: "Mild Cognitive Impairment", 5: "Dementia"}


def translate(type_dem):
    return b_Translate[type_dem]


def getPredict(AGE, PTGENDER, PTMARRY, MMSE, PTRACCAT):

    my_data = pd.DataFrame(data={"AGE": AGE, "PTGENDER": PTGENDER,
                                "PTMARRY": PTMARRY, "MMSE": MMSE, "PTRACCAT": PTRACCAT})

    filename = 'xgbcc.sav'
    loaded_model = pickle.load(open(filename, 'rb'))
    y_pred = int(loaded_model.predict(my_data))
    return translate(y_pred)


app = Flask(__name__)
cors = CORS(app)


@app.route('/text', methods=["POST"])
def post():
    input_json = request.get_json(force=True)
    AGE = [input_json['AGE']]
    PTGENDER = [input_json['PTGENDER']]
    PTMARRY = [input_json['PTMARRY']]
    PTRACCAT = [input_json['PTRACCAT']]
    MMSE = [input_json['MMSE']]

    personality = getPredict(AGE, PTGENDER, PTMARRY, MMSE, PTRACCAT)

    return jsonify(personality)


if __name__ == '__main__':
    app.run()
