package edu.miu.report.grpc;

import edu.miu.report.hello.HelloRequest;
import edu.miu.report.hello.HelloResponse;
import edu.miu.report.hello.HelloServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void sayHello(HelloRequest request,
                         io.grpc.stub.StreamObserver<HelloResponse> responseObserver) {
        String name = request.getName();
        HelloResponse response = HelloResponse.newBuilder()
                .setMessage("Hello " + name)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}