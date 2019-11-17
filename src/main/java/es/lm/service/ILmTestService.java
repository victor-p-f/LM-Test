package es.lm.service;

import java.util.List;

import es.lm.vo.ItemVO;
import es.lm.vo.ReceiptVO;

public interface ILmTestService {

	/**
	 * Method that generate the final receipt to show in a page
	 * @param jsonList 
	 * @return
	 */
	ReceiptVO getReceipt(List<ItemVO> jsonList);

}
