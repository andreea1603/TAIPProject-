import base64

from flask import *
from flask_cors import CORS

from mlService1 import get_result

app = Flask(__name__)
CORS(app)
cors = CORS(app, resources={
    r"/*": {
        "origins": "*"
    }
})


@app.route("/getAnalysis", methods=['POST'])
def get_sentiment_analysis():
    try:
        body = request.get_json()

        base64Img = body["imageAsBase64"]

        result = get_result(base64Img)
        print(result)
        return jsonify(result)
    except Exception as e:
        print(e)
        return jsonify({'MRI scan failed': 0})


if __name__ == '__main__':
    app.run(debug=True, threaded=True, processes=1)
