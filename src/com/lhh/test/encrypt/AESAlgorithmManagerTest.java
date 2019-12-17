package com.lhh.test.encrypt;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lhh.util.encrypt.AESAlgorithmManager;

import junit.framework.Assert;

public class AESAlgorithmManagerTest {

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
	public void testGetAESEncode() {
		String KEY="CDCC4D2BC81546FCFC1FA659CFAE8171";
		byte[] encoded;
		try {
			encoded = AESAlgorithmManager.getAESEncode(KEY, "FC:D5:D9:02:FE:25");
			String encodeStr=new String(AESAlgorithmManager.bytes2HexString(encoded));
			Assert.assertEquals(encodeStr, "2be4dfc1ba365b249e83090c2c53174ffe2751858dc829f2bfddfb4083144e47");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAESDecode() {
		String KEY="CDCC4D2BC81546FCFC1FA659CFAE8171";
		byte[] decoded;
		try {
			decoded = AESAlgorithmManager.getAESDecode( KEY, AESAlgorithmManager.hexString2Bytes("2be4dfc1ba365b249e83090c2c53174ffe2751858dc829f2bfddfb4083144e47"));
	        String mac=new String(decoded);
			Assert.assertEquals(mac, "FC:D5:D9:02:FE:25");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
