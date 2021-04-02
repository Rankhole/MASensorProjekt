from flask import Flask
from flask_sqlalchemy import SQLAlchemy
import requests
import atexit
from apscheduler.schedulers.background import BackgroundScheduler

app = Flask(__name__)
host = "192.168.2.156"

# variables, change according to your needs!
get_sensor_data_url = "http://" + host + ":5000/readsensor"
postgresql_user = "flask"
postgresql_password = ""
postgresql_db = "sensordb"
postgresql_host = "localhost"
postgresql_port = "5432"

db = SQLAlchemy()
POSTGRES = {'user': postgresql_user, 'pw': postgresql_password, 'db': postgresql_db, 'host': postgresql_host,
            'port': postgresql_port}
# Setup DB
app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://%(user)s:\
%(pw)s@%(host)s:%(port)s/%(db)s' % POSTGRES

db.init_app(app)

@app.route('/')
def mainPage():
    return 'Hey!'


def update_database():
    try:
        # Request json with data of our sensor
        result = requests.get(get_sensor_data_url).json()
        print(str(result))
    except Exception as error:
        print(error)


if __name__ == '__main__':
    scheduler = BackgroundScheduler()
    scheduler.add_job(func=update_database, trigger="interval", seconds=2)
    scheduler.start()
    app.run(debug=False, host="0.0.0.0")
    atexit.register(lambda: scheduler.shutdown())
