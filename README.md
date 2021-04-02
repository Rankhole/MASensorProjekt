# MASensorProjekt
Git repo vom Projekt für den Kurs Ausgewählte Kapitel mobiler Anwendungen

# Raspberry Pi
Benötigt wird:

- Raspberry Pi & OS
- DHT22 Temperatur & Luftfeuchtigkeitssensor
- Python 3
- Adafruit library, installieren wie hier: https://learn.adafruit.com/webide/installation

## Flask Server
Der Rest Server erstellt einen Endpoint auf dem 5000er Port vom Raspberry pi, "/readsensor".
Die IP vom Raspberry Pi muss am besten statisch gemappt sein, damit man über das lokale Netzwerk dran kommt.
Zuerst den flask Server starten:
```
sudo python3 RaspberryPi/flaskRestServer/temp_read_flask_server.py
```
Anschließend einen GET auf den neuen Endpoint ausführen, zur Validierung:
```
GET http://IP.VOM.RASPBERRY.PI:5000/readsensor
```
Das Resultat ist eine JSON mit den Daten, die wie folgt ausschaut:
```
{
  "humidity": 31.8, 
  "temperature": 21.3
}
```
Das wars auch schon! Wie man den Raspberry Pi exposed ist natürlich jedem selbst überlassen. Bei mir hängt er lokal an eine statisch gemappte IP, sodass alle meine Geräte im Router ihn über 192.168.2.156 erreichen können.
