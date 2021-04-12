import time

import board

import adafruit_dht

from flask import Flask
from flask import jsonify
from flask import Response


# Initial the dht device, with data pin connected to:

# dhtDevice = adafruit_dht.DHT22(board.D4)



# you can pass DHT22 use_pulseio=False if you wouldn't like to use pulseio.

# This may be necessary on a Linux single board computer like the Raspberry Pi,

# but it will not work in CircuitPython.

dhtDevice = adafruit_dht.DHT22(board.D17, use_pulseio=False)

app = Flask(__name__)

@app.route('/readsensor')
def read_sensor_data():
    try:

        # Print the values to the serial port

        temperature_c = dhtDevice.temperature

        humidity_percent = dhtDevice.humidity

        print("Temp: {:.1f} C    Humidity: {}% ".format(temperature_c, humidity_percent))

        return jsonify({'temperature': temperature_c, 'humidity': humidity_percent})

    except RuntimeError as error:

        # Errors happen fairly often, DHT's are hard to read, just keep going

        print(error.args[0])

        return Response(status=503)
    except Exception as error:
        print(error)
        return Response(status=500)


if __name__ == '__main__':
    app.run(debug=True, host="0.0.0.0")

