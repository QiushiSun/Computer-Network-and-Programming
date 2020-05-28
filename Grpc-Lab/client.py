import grpc
import network_pb2_grpc
import network_pb2
channel = grpc.insecure_channel("localhost:9091")
stub = network_pb2_grpc.NetworkStub(channel)
resp= network_pb2.Reply = stub.CallBack(network_pb2.Request(clientName = "SUNQIUSHI"))
print("reply message is {0}".format(resp.message))