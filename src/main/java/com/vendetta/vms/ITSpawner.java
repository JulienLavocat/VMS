package com.vendetta.vms;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.vendetta.vms.utils.Utils;

public class ITSpawner {

	public static ArrayList<Instance> instances = new ArrayList<Instance>();

	private static AmazonEC2 client;

	public static void init() {

		String[] credts = null;
		try {
			credts = new String(Files.readAllBytes(Paths.get("creditentials.txt"))).split("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.setProperty("aws.accessKeyId", credts[0]);
		System.setProperty("aws.secretKey", credts[1]);

		client = AmazonEC2ClientBuilder.standard()
				.withRegion(Regions.EU_CENTRAL_1)
				.build();
	}

	public static void spawnInstance() {

		String id = Utils.randomString(10);

		RunInstancesRequest rq = new RunInstancesRequest();

		rq.withImageId("ami-0e38e9c0ec341e48f")
		.withInstanceType(InstanceType.T2Micro)
		.withMinCount(1)
		.withMaxCount(1)
		.withKeyName("gs")
		.withSecurityGroupIds("sg-08b2dd2ebf3a2f704");

		RunInstancesResult rs = client.runInstances(rq);
		rs.getReservation().getInstances().forEach(i -> {
			System.out.println("Spawning instance #"+i);
			instances.add(i);
		});
	}

}
