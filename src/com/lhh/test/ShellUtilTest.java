package com.lhh.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lhh.util.LogUtil;
import com.lhh.util.ShellUtil;
import com.lhh.util.ShellUtil.CommandResult;

import android.os.Message;
import android.text.TextUtils;

public class ShellUtilTest {
	private static final String TAG = "ShellUtilTest";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExecCmd() {
		checkPingAddress("www.baidu.com");
	}

	private void checkPingAddress(final String pingAddress) {
		final String PING_COMMAND="ping -c 1 ";

		new Thread() {
			@Override
			public void run() {
				LogUtil.d(TAG, "P:checkPingAddress,pingAddress:" + pingAddress);
				if (!TextUtils.isEmpty(pingAddress)) {
					String validAddress=validPingAddress(pingAddress);
					String[] commands = new String[] { PING_COMMAND + validAddress };
					LogUtil.d(TAG, "p:checkPingAddress,validAddress:" + validAddress);
					CommandResult pingResult = ShellUtil.execCmd(commands, false, true);
					LogUtil.d(TAG, "pingResult:" + pingResult);
					Message message = new Message();
					if (!TextUtils.isEmpty(pingResult.errorMsg)) {
						LogUtil.d(TAG, "errorMsg:"+pingResult.errorMsg);						
					}else if (!TextUtils.isEmpty(pingResult.successMsg)) {
						if(pingResult.successMsg.contains("100% packet loss")){
							LogUtil.d(TAG, "successMsg contain 100% packet loss");
						}else{
							LogUtil.d(TAG, "successMsg:"+pingResult.successMsg);
						}
					}
				}
			}
		}.start();
	}
	
	private String validPingAddress(String pingAddress) {
		if (TextUtils.isEmpty(pingAddress)) {
			return pingAddress;
		} else if (pingAddress.startsWith("http://")) {
			return pingAddress.substring(7);
		} else if (pingAddress.startsWith("https://")) {
			return pingAddress.substring(8);
		}
		return pingAddress;
	}
}
