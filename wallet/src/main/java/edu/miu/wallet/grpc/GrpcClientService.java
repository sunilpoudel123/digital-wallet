//package edu.miu.wallet.grpc;
//
//package com.example.wallet;
//
//import com.example.hello.HelloRequest;
//import com.example.hello.HelloResponse;
//import com.example.hello.HelloServiceGrpc;
//import net.devh.boot.grpc.client.inject.GrpcClient;
//import org.springframework.stereotype.Service;
//
//@Service
//public class GrpcClientService {
//
//    @GrpcClient("user-service")
//    private HelloServiceGrpc.HelloServiceBlockingStub helloServiceStub;
//
//    public String sayHello(String name) {
//        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
//        HelloResponse response = helloServiceStub.sayHello(request);
//        return response.getMessage();
//    }
//}
