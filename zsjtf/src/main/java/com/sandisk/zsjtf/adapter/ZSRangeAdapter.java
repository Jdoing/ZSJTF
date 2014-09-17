package com.sandisk.zsjtf.adapter;

import com.sandisk.zs.ZSRange;
import com.sandisk.zs.exception.ZSContainerException;
import com.sandisk.zs.type.RangeMeta;
import com.sandisk.zs.type.ZSRangeFlags;
import com.sandisk.zsjtf.JTFCommand;
import com.sandisk.zsjtf.command.ZSGetRange;
import com.sandisk.zsjtf.exception.JTFException;
import com.sandisk.zsjtf.global.ZSAdapter;
import com.sandisk.zsjtf.util.Log;

public class ZSRangeAdapter extends ZSAdapter {

	private static long cguid = 0;
	private int keybufSize = 0;
	private long databufSize = 0;
	private int keyLenStart = 0;
	private int KeyLenEnd = 0;
	private int startKey = 0;
	private int endKey = 0;
	private long startSeq = 0;
	private long endSeq = 0;
	private int flags = 0;

	private String keyStart;
	private String keyEnd;
	
	private ZSGetRange zsGetRange;

	public ZSGetRange getZSGetRange() {
		return zsGetRange;
	}

	private ZSRange zsRange;
	
	private JTFCommand jtfCommand;

	public void setJTFCommand(JTFCommand jtfCommand) throws Exception {
		this.jtfCommand = jtfCommand;
	}

	public void getRange() throws Exception {
		try {
			if (jtfCommand instanceof ZSGetRange) {
				this.zsGetRange = (ZSGetRange) jtfCommand;
			} else
				throw new JTFException("Create ZSGetRange error");

			ZSRangeAdapter.cguid = zsGetRange.getCguid();
			this.keybufSize = zsGetRange.getKeybufSize();
			this.databufSize = zsGetRange.getDatabufSize();
			this.keyLenStart = zsGetRange.getKeyLenStart();
			this.KeyLenEnd = zsGetRange.getKeyLenEnd();
			this.startKey = zsGetRange.getStartKey();
			this.endKey = zsGetRange.getEndKey();
			this.startSeq = zsGetRange.getStartSeq();
			this.endSeq = zsGetRange.getEndSeq();
			this.flags = zsGetRange.getFlags();

			if ((keybufSize == 0) && (databufSize == 0) && (startKey == 0)
					&& (endKey == 0) && (startSeq == 0) && (endSeq == 0)
					&& (flags == 0)) {

				Log.logDebug("Initialize the ZSRange without meta paremeter.");
				
				this.zsRange = new ZSRange(cguid);
				// range.begin();
				Log.logDebug("Initialize the ZSRange finish!");

			} else {
				// range query part of the data
				Log.logDebug("Initialize the ZSRange with meta paremeter.");

				RangeMeta meta = new RangeMeta();
				setMeta(meta);
				
				this.zsRange = new ZSRange(meta, cguid);
			}
		} catch (JTFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		zsRange.begin();
	}

	private void setMeta(RangeMeta rmeta) throws Exception {
		if (flags == 0) {
			flags = ZSRangeFlags.START_GT | ZSRangeFlags.END_LE;
		}

//		keybufSize = (keybufSize != 0) ? keybufSize : zsGetRange.MAX_KEY_LEN;
//		databufSize = (databufSize != 0) ? databufSize
//				: zsGetRange.MAX_DATA_LEN;
//		keyLenStart = (keyLenStart != 0) ? keyLenStart : 8;
//		KeyLenEnd = (KeyLenEnd != 0) ? KeyLenEnd : 8;
//		startSeq = (startSeq != 0) ? startSeq : 0;
//		endSeq = (endSeq != 0) ? endSeq : 0;
//		String formatter = String.format("%%0%dd", keyLenStart);
//		String keyStart = String.format(formatter, startKey);
//
//		String fmt = String.format("%%0%dd", KeyLenEnd);
//		String keyEnd = String.format(fmt, endKey);

		rmeta.setFlags(flags);
		rmeta.setStartInfo(keyStart.getBytes(), flags);
		rmeta.setEndInfo(keyEnd.getBytes(), flags);
		rmeta.setStartSeq(startSeq);
		rmeta.setEndSeq(endSeq);
	}

}
