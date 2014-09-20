package com.sandisk.zsjtf.exec;

import com.sandisk.zs.ZSContainer;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.global.ZSCommandExec;
import com.sandisk.zsjtf.util.ContainerManager;
import com.sandisk.zsjtf.util.NameIDMapper;

public class ZSDeleteContainerExec extends ZSCommandExec {

	private ZSContainer zsContainer;

	public ZSDeleteContainerExec(JTFCommand jtfCommand) {
		super(jtfCommand);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String Execute() {
		// TODO Auto-generated method stub
		try {
			zsContainer.drop();
			return  jtfCommand.handleSuccessReturn();
		} catch (ZSContainerException e) {
			// TODO Auto-generated catch block
			return jtfCommand.handleServerErrorReturn(e);
		}
	}

	@Override
	public void setZSEntry(Object zsEntry) throws JTFException {
		// TODO Auto-generated method stub
		if (zsEntry instanceof ZSContainer) {
			zsContainer = (ZSContainer) zsEntry;
		} else {
			throw new JTFException("ZSEntry do not match ZSContainer");
		}
	}
}
