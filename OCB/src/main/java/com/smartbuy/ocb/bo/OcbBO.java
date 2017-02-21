package com.smartbuy.ocb.bo;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.smartbuy.ocb.dao.OcbDAO;
import com.smartbuy.ocb.dto.SkuDTO;
import com.smartbuy.ocb.exception.OCBException;

public class OcbBO 
{
	
	OcbDAO ocbDao = null;

	public OcbBO() 
	{
		ocbDao = new OcbDAO();
	}
	
	// getting all the skus
	public List<SkuDTO> executeGetSkuforStore(String storeNumber) throws OCBException{
		try
		{
			List<SkuDTO> listofSkus = ocbDao.fetchSkuforStore(storeNumber);
			int ordQty = 0;
			
		    int newPoNum = ocbDao.getLastPoNum() + 1; 
										
			// different logic and operation on skuList
			
			for(SkuDTO s : listofSkus)
			{
						
				if (s.getPoCrt().equals("Y"))
				{
					if (s.getordReq() <= s.getVendMin())
					{
						ordQty = s.getVendMin();
					}
					else
					{
						ordQty = s.getVendMin() * 2;
					}
					
					ocbDao.insertSarPoTable(newPoNum, s.getSkuNumber(), ordQty);
					
				}
			}
			
		ocbDao.updateLastPoNum(newPoNum);
			
		}
		catch(Exception e)
		{
			// logger.error(Log this);
			throw new OCBException(e);
		}
		
		return null;
	}
}
