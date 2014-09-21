package com.sandisk.zsjtf.command;

import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.type.ContainerProperty;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.util.ContainerManager;
import com.sandisk.zsjtf.util.NameIDMapper;

public class TestZSDeleteContainer {
	private ZSDeleteContainer zsDeleteContainer;
	private ContainerProperty mockContainerProperty;
	private ZSContainer container;
	private Long containerID = 5L;
	private String containerName = "testContainerName";

	private String rawCommand;

	@Before
	public void setUp() throws Exception {
		mockContainerProperty = createMock("mockContainerProperty",
				ContainerProperty.class);

		container = new ZSContainer(containerName, mockContainerProperty);

		NameIDMapper.getInstance().setNameIDMap(containerName, containerID);
		ContainerManager.getInstance().setContainer(containerID, container);
	}

	@Test
	public void test() {
		rawCommand = "ZSDeleteContainer cguid=5";
		try {
			zsDeleteContainer = new ZSDeleteContainer(rawCommand);
			assertNotNull(zsDeleteContainer);
			
			Object object = zsDeleteContainer.getZSEntry();
			
			assertNotNull(object);
			assertEquals(ZSContainer.class, object.getClass());
			
		} catch (JTFException e) {
			// TODO Auto-generated catch block
			fail(e.toString());
		} catch (ZSContainerException e) {
			// TODO Auto-generated catch block
			fail(e.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail(e.toString());
		}
	}
}
