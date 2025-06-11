package edu.miu.report.grpc;

import edu.miu.report.hello.HelloRequest;
import edu.miu.report.hello.HelloResponse;
import edu.miu.report.hello.HelloServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class HelloServer {

    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(9073)
                .addService(new HelloServiceImpl())
                .build()
                .start();

        System.out.println("Server started on port 9073");

        server.awaitTermination();
    }

    static class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

        @Override
        public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
            String name = request.getName();
            HelloResponse response = HelloResponse.newBuilder()
                    .setMessage("Hello " + name)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}