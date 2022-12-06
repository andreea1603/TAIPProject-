import pickle

import pandas as pd
from flask_cors import CORS
from flask import Flask, request, jsonify

b_Translate = {0: "CN", 1: "SMC", 2: "EMCI", 3: "LMCI", 4: "MCI", 5: "Dementia"}


def translate(type_dem):
    return b_Translate[type_dem]


def getPredict(AGE, PTGENDER, PTEDUCAT, CDRSB,
               ADAS11, ADAS13, ADASQ4, MMSE, RAVLT_immediate, RAVLT_learning, RAVLT_forgetting, RAVLT_perc_forgetting,
               FAQ, mPACCdigit, mPACCtrailsB, CDRSB_bl, ADAS11_bl, ADAS13_bl, ADASQ4_bl, MMSE_bl, RAVLT_immediate_bl,
               RAVLT_learning_bl,
               RAVLT_forgetting_bl, RAVLT_perc_forgetting_bl, FAQ_bl, mPACCdigit_bl, mPACCtrailsB_bl):

    my_data = pd.DataFrame(data={"AGE": AGE, "PTGENDER": PTGENDER,
                                "PTEDUCAT": PTEDUCAT, "CDRSB": CDRSB, "ADAS11": ADAS11, "ADAS13": ADAS13,
                                "ADASQ4": ADASQ4, "MMSE": MMSE, "RAVLT_immediate": RAVLT_immediate,
                                "RAVLT_learning": RAVLT_learning, "RAVLT_forgetting": RAVLT_forgetting,
                                "RAVLT_perc_forgetting": RAVLT_perc_forgetting, "FAQ": FAQ,
                                "mPACCdigit": mPACCdigit, "mPACCtrailsB": mPACCtrailsB, "CDRSB_bl": CDRSB_bl,
                                "ADAS11_bl": ADAS11_bl, "ADAS13_bl": ADAS13_bl, "ADASQ4_bl": ADASQ4_bl,
                                "MMSE_bl": MMSE_bl, "RAVLT_immediate_bl": RAVLT_immediate_bl,
                                "RAVLT_learning_bl": RAVLT_learning_bl, "RAVLT_forgetting_bl": RAVLT_forgetting_bl,
                                "RAVLT_perc_forgetting_bl": RAVLT_perc_forgetting_bl,
                                "FAQ_bl": FAQ_bl, "mPACCdigit_bl": mPACCdigit_bl, "mPACCtrailsB_bl": mPACCtrailsB_bl})

    filename = 'xgbc.sav'
    loaded_model = pickle.load(open(filename, 'rb'))
    y_pred = int(loaded_model.predict(my_data))
    return translate(y_pred)


app = Flask(__name__)
cors = CORS(app)


@app.route('/text', methods=["POST"])
def post():
    input_json = request.get_json(force=True)
    AGE = input_json['AGE']
    PTGENDER = input_json['PTGENDER']
    PTEDUCAT = input_json['PTEDUCAT']
    CDRSB = input_json['CDRSB']
    ADAS11 = input_json['ADAS11']
    ADAS13 = input_json['ADAS13']
    ADASQ4 = input_json['ADASQ4']
    MMSE = input_json['MMSE']
    RAVLT_immediate = input_json['RAVLT_immediate']
    RAVLT_learning = input_json['RAVLT_learning']
    RAVLT_forgetting = input_json['RAVLT_forgetting']
    RAVLT_perc_forgetting = input_json['RAVLT_perc_forgetting']
    FAQ = input_json['FAQ']
    mPACCdigit = input_json['mPACCdigit']
    mPACCtrailsB = input_json['mPACCtrailsB']
    CDRSB_bl = input_json['CDRSB_bl']
    ADAS11_bl = input_json['ADAS11_bl']
    ADAS13_bl = input_json['ADAS13_bl']
    ADASQ4_bl = input_json['ADASQ4_bl']
    MMSE_bl = input_json['MMSE_bl']
    RAVLT_immediate_bl = input_json['RAVLT_immediate_bl']
    RAVLT_learning_bl = input_json['RAVLT_learning_bl']
    RAVLT_forgetting_bl = input_json['RAVLT_forgetting_bl']
    RAVLT_perc_forgetting_bl = input_json['RAVLT_perc_forgetting_bl']
    FAQ_bl = input_json['FAQ_bl']
    mPACCdigit_bl = input_json['mPACCdigit_bl']
    mPACCtrailsB_bl = input_json['mPACCtrailsB_bl']

    personality = getPredict(AGE, PTGENDER, PTEDUCAT, CDRSB,
                             ADAS11, ADAS13, ADASQ4, MMSE, RAVLT_immediate, RAVLT_learning, RAVLT_forgetting,
                             RAVLT_perc_forgetting,
                             FAQ, mPACCdigit, mPACCtrailsB, CDRSB_bl, ADAS11_bl, ADAS13_bl, ADASQ4_bl, MMSE_bl,
                             RAVLT_immediate_bl, RAVLT_learning_bl,
                             RAVLT_forgetting_bl, RAVLT_perc_forgetting_bl, FAQ_bl, mPACCdigit_bl, mPACCtrailsB_bl)

    return jsonify(personality)


if __name__ == '__main__':
    app.run()
