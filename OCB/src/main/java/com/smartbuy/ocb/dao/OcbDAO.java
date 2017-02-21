package com.smartbuy.ocb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smartbuy.ocb.dto.SkuDTO;
import com.smartbuy.ocb.exception.OCBException;
import com.smartbuy.ocb.util.DBConnectionUtil;


// Data Access Object
public class OcbDAO 
{
	
	public OcbDAO() 
	{
		
	}

	//Fetch all the list of sku information
	public List<SkuDTO> fetchSkuforStore(String storeNumber) throws OCBException
	{
		List<SkuDTO> listofSkus =new ArrayList<SkuDTO>();
		Connection conn = null;
		
		String sql = "SELECT a.sku_number, SKU_DESC,"
				     + "IF (SKU_VELOCITY * TRK_DLVR_TIME_DAYS < PACK_SIZE, PACK_SIZE,"
				     + "SKU_VELOCITY * TRK_DLVR_TIME_DAYS) as ORD_REQ, VENDR_MIN,"
		             + " IF (SHELF_QTY  +  IN_STR_QTY < SKU_RCMD_THRD, 'Y', 'N') as PO_CR"
				     + " FROM sku_store a, sar_parm b, sku c"
					 + " WHERE a.sku_number = b.sku_number"
					 + " AND   a.sku_number = c.sku_number"
					 + " AND   store_num = ?";
					  
		try
		{
			// DO DB Operation here
			conn = DBConnectionUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, storeNumber);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				SkuDTO sku = new SkuDTO();
				
				sku.setSkuNumber(rs.getString(1));
				sku.setSkuName(rs.getString(2));
				sku.setOrdReq(rs.getInt(3));
				sku.setVendMin(rs.getInt(4));
				sku.setPoCrt(rs.getString(5));
				listofSkus.add(sku);
			}
		}
		catch(Exception e)
		{
			// logger.error(Log this exception);
			throw new OCBException(e);
		}
			DBConnectionUtil.closeConnection(conn);
			
		return listofSkus;
	
	}
	
	//insert the new po number into the sar_po table
	public void insertSarPoTable(int newPoNum, String skuNumber, int ordQty) throws OCBException 
	{
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO sar_po"
				+ "(PO_NUMBER, SKU_NUMBER, STR_NUMBER, ORDR_QTY) VALUES"
				+ "(?,?,?,?)";
		try
		{
			
			conn = DBConnectionUtil.getConnection();
			preparedStatement = conn.prepareStatement(insertTableSQL);

			preparedStatement.setInt(1, newPoNum);
			preparedStatement.setString(2, skuNumber);
			preparedStatement.setString(3, "501");
			preparedStatement.setInt(4, ordQty);
			

			// execute insert SQL statement
			preparedStatement.executeUpdate();

			System.out.println("Record is inserted into SAR_PO table!");
			
		}
		catch(Exception e)
		{
			// logger.error(Log this exception);
			throw new OCBException(e);
		}
		
		DBConnectionUtil.closeConnection(conn);
			
	}
	
	//update the last po field in the po_table
	public void updateLastPoNum(int PoNumber) throws OCBException
	{
		Connection conn = null;
		PreparedStatement ps = null;

		String updateTableSQL = "update po_number set last_po_num =?";
		
		try
		{
			conn = DBConnectionUtil.getConnection();
			ps = conn.prepareStatement(updateTableSQL);

			ps.setInt(1, PoNumber);
			
			// execute insert SQL statement
			ps.executeUpdate();

			System.out.println("Record is inserted into po_number table!");
			
		}
		catch(Exception e)
		{
			// logger.error(Log this exception);
			throw new OCBException(e);
		}
		
		DBConnectionUtil.closeConnection(conn);
			
	}
	
	//update the last po field in the po_table
		public int getLastPoNum() throws OCBException
		{
			Connection conn      = null;
			PreparedStatement ps = null;

			int poNumber = 0;
			String getPoSql = "Select LAST_PO_NUM  from po_number";
			
			try
			{
				conn = DBConnectionUtil.getConnection();
				ps = conn.prepareStatement(getPoSql);
				ResultSet rs=ps.executeQuery();
				System.out.println(getPoSql);
				while(rs.next())
				{
					poNumber = rs.getInt("LAST_PO_NUM");
							
				}

			}
			catch(Exception e)
			{
				// logger.error(Log this exception);
				throw new OCBException(e);
			}
			
			DBConnectionUtil.closeConnection(conn);
			
			return poNumber;
				
		}
}
