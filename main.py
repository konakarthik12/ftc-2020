import numpy as np
import transformations as tf
import math as m
from websocket import create_connection

H_aeroRef_T265Ref = np.array([[0, 0, -1, 0], [1, 0, 0, 0], [0, -1, 0, 0], [0, 0, 0, 1]])
H_T265body_aeroBody = np.linalg.inv(H_aeroRef_T265Ref)

print("Lettuce")
ws = create_connection("ws://192.168.137.93:53717")
print("Sending 'Hello, World'...")
ws.send("Hello, World")
print("Sent")
print("Reeiving...")

stuffy = 0


def get_alt_euler(param):
    # w = data.rotation.w
    # x = -data.rotation.z
    # y = data.rotation.x
    # z = -data.rotation.y
    w = param[0]
    x = -param[3]
    y = param[1]
    z = -param[2]
    try:
        pitch = -m.asin(2.0 * (x * z - w * y)) * 180.0 / m.pi
        roll = m.atan2(2.0 * (w * x + y * z), w * w - x * x - y * y + z * z) * 180.0 / m.pi
        yaw = m.atan2(2.0 * (w * z + x * y), w * w + x * x - y * y - z * z) * 180.0 / m.pi
        print("RPY * [deg]:[ {0:.7f}, {1:.7f}, {2:.7f}]".format(roll, pitch, yaw))
        return [roll, pitch, yaw]
    except ValueError:
        print("failed")

    # print("RPY * [deg]:[ {0:.7f}, {1:.7f}, {2:.7f}]".format(roll, pitch, yaw))


def get_euler(param):
    global stuffy
    body = tf.quaternion_matrix(
        param)  # in transformations, Quaternions w+ix+jy+kz are represented as [w, x, y, z]!

    # transform to aeronautic coordinates (body AND reference frame!)
    aero_body = H_aeroRef_T265Ref.dot(body.dot(H_T265body_aeroBody))

    rpy_rad = np.array(
        tf.euler_from_matrix(aero_body, 'sxyz'))  # Rz(yaw)*Ry(pitch)*Rx(roll) body w.r.t. reference frame
    stuffy = stuffy + 1
    print("Frame #{}".format(stuffy))
    print("RPY [deg]:\t{}".format(rpy_rad * 180 / m.pi))
    return rpy_rad
    # print("Alternative:\t" + get_alt_euler(param))


def compare(apples, oranges):
    print(abs(apples[0] - oranges[0]), abs(apples[1] - oranges[1]), abs(apples[2] - oranges[2]))


while (True):
    result = ws.recv()
    a = get_euler(result.split(','))
    b = get_alt_euler([float(num) for num in result.split(',')])
    compare(a, b)
