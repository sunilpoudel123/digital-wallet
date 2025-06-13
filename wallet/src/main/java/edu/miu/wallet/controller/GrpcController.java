package edu.miu.wallet.controller;

import edu.miu.wallet.grpc.GrpcClientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grpc")
public class GrpcController {

    private final GrpcClientService grpcClientService;

    public GrpcController(GrpcClientService grpcClientService) {
        this.grpcClientService = grpcClientService;
    }

    @PostMapping("/create")
    public String createWallet(@RequestParam String name) {
        return grpcClientService.sayHello(name);
    }
}