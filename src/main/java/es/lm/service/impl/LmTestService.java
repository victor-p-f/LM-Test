package es.lm.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import es.lm.enums.KindEnum;
import es.lm.service.ILmTestService;
import es.lm.vo.ItemVO;
import es.lm.vo.ReceiptVO;

@Service
public class LmTestService implements ILmTestService {

	private static final String IMPORTED = "imported";

	@Value("${item.rate}")
	private long rate;

	@Value("${imported.rate}")
	private long importedRate;

	public ReceiptVO getReceipt(List<ItemVO> jsonList) {
		ReceiptVO receipt = new ReceiptVO();
		List<ItemVO> result = new ArrayList<>();
		BigDecimal initialPriceResult = BigDecimal.ZERO;
		BigDecimal totalImport = BigDecimal.ZERO;
		for (ItemVO item : jsonList) {
			BigDecimal internalRate = BigDecimal.ZERO;
			if (KindEnum.OTHER.equals(item.getKind())) {
				internalRate = internalRate.add(new BigDecimal(rate));
			}
			if (item.getName().contains(IMPORTED)) {
				internalRate = internalRate.add(new BigDecimal(importedRate));
			}
	
			initialPriceResult = initialPriceResult.add(item.getPrice());

			BigDecimal division = new BigDecimal(100).add(internalRate).divide(new BigDecimal(100));
			BigDecimal price = item.getPrice().multiply(division);
			price = rounding(price);
			totalImport = totalImport.add(price);
			
			ItemVO processedItem = new ItemVO();
			processedItem.setName(item.getName());
			processedItem.setKind(item.getKind());
			processedItem.setPrice(price);
			result.add(processedItem);
		}
		receipt.setTaxes(totalImport.subtract(initialPriceResult).doubleValue());
		receipt.setTotalPrice(totalImport.doubleValue());
		receipt.setItems(result);
		return receipt;
	}

	private BigDecimal rounding(BigDecimal value) {
		value = value.setScale(3, RoundingMode.FLOOR); 
		
		String[] valueArray = value.toPlainString().split("");
		String internalResult;
		if (5 > Integer.valueOf(valueArray[valueArray.length-2]) && 0 < Integer.valueOf(valueArray[valueArray.length-1])) {
			valueArray[valueArray.length-2]= "5";
			valueArray[valueArray.length-1]= "0";
			internalResult = String.join("", valueArray);
			
		} else {
			internalResult = Double.toString(value.doubleValue());
		}
		value = new BigDecimal(internalResult);
		return value.setScale(2, RoundingMode.HALF_UP);
	}
}
