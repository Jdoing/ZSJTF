/*
 * File: ZSCloseContainer.java
 * Author: qyu, rchen, yjiang
 *
 * SanDisk Proprietary Material, © Copyright 2014 SanDisk, all rights reserved.
 * http://www.sandisk.com
 * THIS IS NOT A CONTRIBUTION.
 */
package com.sandisk.zsjtf.command;

import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.util.ContainerManager;
import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.exception.ZSException;

/**
 * ZSCloseContainer command class.
 *
 * @author rchen
 *
 *         args: cguid=%u
 *
 *         must have: cguid
 *
 *         return: success: OK failed: SERVER_ERROR %s | CLIENT_ERROR %s
 */

public class ZSCloseContainer extends JTFCommand {

	private String ZSCommandExecName = "ZSCloseContainerExec";
	private ZSContainer container;

	public ZSCloseContainer(String rawCommand) throws JTFException {
		super(rawCommand);
		// TODO Auto-generated constructor stub
		getProperties();

	}

	/* Command arg list start */
	private Long containerID;

	/* Command arg list end */

	@Override
	public String execute() {

		return zsCommandExec.Execute();

	}

	private void getProperties() throws JTFException {
		containerID = getLongProperty(CGUID, true);
	}

	// private void closeContainer() throws JTFException, ZSContainerException
	// {
	// ContainerManager.getInstance().getContainer(containerID).close();
	// }

	@Override
	public String getZSCommandExecName() {
		// TODO Auto-generated method stub
		return ZSCommandExecName;
	}

	@Override
	public Object createZSEntry() throws ZSContainerException, JTFException,
			Exception {
		// TODO Auto-generated method stub
		container = ContainerManager.getInstance().getContainer(containerID);
		return container;
	}
}
