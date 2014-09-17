package com.sandisk.zsjtf.exec;

import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zsjtf.adapter.ZSRangeAdapter;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.global.ZSAdapter;
import com.sandisk.zsjtf.global.ZSCommandExec;
import com.sandisk.zsjtf.util.Log;

public class ZSGetRangeExec extends ZSCommandExec {

	private ZSRangeAdapter zsRangeAdapter;

	public ZSGetRangeExec(ZSAdapter zsAdapter) {
		super(zsAdapter);
	}

	@Override
	public String Execute() {
		// TODO Auto-generated method stub

		try {
			if (zsAdapter instanceof ZSRangeAdapter) {
				zsRangeAdapter = (ZSRangeAdapter) zsAdapter;
			} else {
				throw new JTFException("ZSAdapter do not match ZSRangeAdapter");
			}

			Log.logDebug("Start to GetRange");

			zsRangeAdapter.getRange();

			Log.logDebug("GetRange finish");

			return "OK";

		} catch (JTFException e) {
			return "CLIENT_ERROR:" + e.toString();
		} catch (ZSContainerException e) {
			return "SERVER_ERROR ZS_FAILURE:" + e.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "SERVER_ERROR ZS_FAILURE:" + e.toString();
		}

	}

}
