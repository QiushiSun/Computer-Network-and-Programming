import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.concurrent.TimeUnit;
public class GrpcClient {

    private final ManagedChannel my_CHANNEL;
    private final NetworkGrpc.NetworkBlockingStub my_STUB;

    public static void main(String[] args) throws InterruptedException {
        GrpcClient client = new GrpcClient("localhost",9091);
        client.Greeting("here!!");
    }

    public GrpcClient(String host,int port){
        my_CHANNEL = ManagedChannelBuilder.forAddress(host,port)
                .usePlaintext()
                .build();
        my_STUB = NetworkGrpc.newBlockingStub(my_CHANNEL);
    }

    public void shutdown() throws InterruptedException {
        my_CHANNEL.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void Greeting(String name){
        Request request= Request.newBuilder().setClientName(name).build();
        Reply response = my_STUB.callBack(request);
        System.out.print(response.getMessage());
    }
}
