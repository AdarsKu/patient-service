package com.service.patient.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.proto.BillingRequest;
import com.proto.BillingResponse;
import com.proto.BillingServiceGrpc;
import com.proto.BillingServiceGrpc.BillingServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PreDestroy;

@Service
public class BillingServiceGrpcClient {

	private final BillingServiceBlockingStub blockingStub;
	private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);
	private final ManagedChannel channel;

	//
	public BillingServiceGrpcClient(
			@Value("${billing.service.address:localhost}") String serverAdd,
			@Value("${billing.service.grpc.port:9002}") int serverPort) {

		log.info("Connecting to Billing Service GRPC service at {}:{}", serverAdd, serverPort);

		// Create channel and stub
		this.channel = ManagedChannelBuilder.forAddress(serverAdd, serverPort).usePlaintext().build();

		this.blockingStub = BillingServiceGrpc.newBlockingStub(channel);
	}

	public BillingResponse createBillingAccount(String patientId, String name, String email) {

		BillingRequest request = BillingRequest.newBuilder().setPatientId(patientId).setName(name).setEmail(email)
				.build();

		BillingResponse response = blockingStub.createBillingAccount(request);
		log.info("Received Response from billing service via GRPC {}", response);
		return response;
	}

	// Shutdown the channel when Spring context is closing
	@PreDestroy
	public void shutdown() {
		log.info("Shutting down BillingService gRPC channel");
		if (channel != null && !channel.isShutdown()) {
			channel.shutdown();
		}
	}
}
