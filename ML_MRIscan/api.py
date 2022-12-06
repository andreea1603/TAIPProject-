import base64

from flask import Flask, jsonify, request
from flask_cors import CORS

from mlService1 import get_result

app = Flask(__name__)
CORS(app)
cors = CORS(app, resources={
    r"/*": {
        "origins": "*"
    }
})


@app.route("/getAnalysis", methods=['GET'])
def get_sentiment_analysis():
    try:
        url = request.args.get('photo')
        print(url)
        x = len(url)
        print(x)
        decodeit = open('imgToSave', 'wb')
        decodeit.write(base64.b64decode(url))
        decodeit.close()
        # fileP = url[0:50]
        # filename = fileP.replace('/', '') + '.jpg'
        filename = r'MRI_of_Human_Brain.jpg'
        result = get_result(filename)

        return jsonify(result)
    except Exception as e:
        print(e)
    return jsonify({'MRI scan failed': 0})


if __name__ == '__main__':
    app.run(debug=True, threaded=True, processes=1)
