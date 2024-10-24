'''
    Integrantes:
        - Esteban Gabriel Fernández Arrúa - 5425495
        - Oscar Augusto Bianciotto Lobasso - 4850464
'''

import sys
import datetime
import configparser
import requests
from requests.structures import CaseInsensitiveDict
import datetime
from datetime import timedelta

api_personas_url_base = None
archivo_config = 'ConfigFile.properties'

def cargar_variables():
    config = configparser.RawConfigParser()
    config.read(archivo_config)

    global api_personas_url_listar, api_personas_url_crear, api_personas_url_actualizar, api_personas_url_eliminar
    api_personas_url_listar = config.get('SeccionApi', 'api_personas_url_listar')
    api_personas_url_crear = config.get('SeccionApi', 'api_personas_url_crear')
    api_personas_url_actualizar = config.get('SeccionApi', 'api_personas_url_actualizar')
    api_personas_url_eliminar = config.get('SeccionApi', 'api_personas_url_eliminar')

def listar():
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"
    r = requests.get(api_personas_url_listar, headers=headers)
    if (r.status_code == 200):
        listado = r.json()
        for item in listado['data']:
            print( "      " + str(item) )
    else:
        print( "Error " + str(r.status_code))

def crear(cedula: int, nombre: str, apellido: str):
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"
    datos = {
             'cedula': cedula,
             'nombre' : nombre,
             'apellido' : apellido
            }
    r = requests.post(api_personas_url_crear, headers=headers, json=datos)
    if (r.status_code >= 200 and r.status_code < 300):
        print(r)
    else:
        print( "Error " + str(r.status_code))
        print(str(r.json()))

def actualizar(cedula: int, nombre: str, apellido: str):
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"
    datos = {
             'cedula': cedula,
             'nombre' : nombre,
             'apellido' : apellido
            }
    r = requests.put(api_personas_url_actualizar, headers=headers, json=datos)
    if (r.status_code >= 200 and r.status_code < 300):
        print(r)
    else:
        print( "Error " + str(r.status_code))
        print(str(r.json()))

def eliminar(cedula: int):
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"
    r = requests.delete(api_personas_url_eliminar.replace(":id", str(cedula)), headers=headers)
    if (r.status_code >= 200 and r.status_code < 300):
        print(r)
    else:
        print( "Error " + str(r.status_code))
        print(str(r.json()))

#######################################################
######  Procesamiento principal
#######################################################
print("Iniciando " + datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"))
cargar_variables()

print("Primer listado de personas:")
listar()
print("________________")

print("Crear nueva persona:")
cedula = int(input("Ingrese cedula: "))
nombre = input("Ingrese nombre: ")
apellido = input("Ingrese apellido: ")
crear(cedula, nombre, apellido)

print("________________")
print("Segundo listado de personas:")
listar()

print("Actualizar una persona:")
cedula = int(input("Ingrese cedula: "))
nombre = input("Ingrese nombre: ")
apellido = input("Ingrese apellido: ")
actualizar(cedula, nombre, apellido)

print("________________")
print("Tercer listado de personas:")
listar()

print("Eliminar una persona:")
cedula = int(input("Ingrese cedula: "))
eliminar(cedula)

print("________________")
print("Cuarto listado de personas:")
listar()

print("Finalizando " + datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"))
