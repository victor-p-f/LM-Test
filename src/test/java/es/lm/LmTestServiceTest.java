package es.lm;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import es.lm.enums.KindEnum;
import es.lm.service.impl.LmTestService;
import es.lm.vo.ItemVO;
import es.lm.vo.ReceiptVO;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableConfigurationProperties
public class LmTestServiceTest {

	@Autowired
	private LmTestService lmTestService;

	
	@Test
	public void testReceipt1() {
		
		ReceiptVO receipt = lmTestService.getReceipt(this.generateInput1());
		assertEquals("book", receipt.getItems().get(0).getName());
		assertEquals(Double.valueOf("12.49"), Double.valueOf(receipt.getItems().get(0).getPrice().doubleValue()));
		assertEquals("music CD", receipt.getItems().get(1).getName());
		assertEquals(Double.valueOf("16.49"), Double.valueOf(receipt.getItems().get(1).getPrice().doubleValue()));
		assertEquals("chocolate bar", receipt.getItems().get(2).getName());
		assertEquals(Double.valueOf("0.85"), Double.valueOf(receipt.getItems().get(2).getPrice().doubleValue()));
		
		assertEquals(Double.valueOf("1.50"), Double.valueOf(receipt.getTaxes()));
		assertEquals(Double.valueOf("29.83"), Double.valueOf(receipt.getTotalPrice()));
 
	}
	
	@Test
	public void testReceipt2() {
		
		ReceiptVO receipt = lmTestService.getReceipt(this.generateInput2());
		assertEquals("imported box of chocolates", receipt.getItems().get(0).getName());
		assertEquals(Double.valueOf("10.50"), Double.valueOf(receipt.getItems().get(0).getPrice().doubleValue()));
		assertEquals("imported bottle of perfume", receipt.getItems().get(1).getName());
		assertEquals(Double.valueOf("54.65"), Double.valueOf(receipt.getItems().get(1).getPrice().doubleValue()));
		
		assertEquals(Double.valueOf("7.65"), Double.valueOf(receipt.getTaxes()));
		assertEquals(Double.valueOf("65.15"), Double.valueOf(receipt.getTotalPrice()));
 
	}
	
	@Test
	public void testReceipt3() {
		
		ReceiptVO receipt = lmTestService.getReceipt(this.generateInput3());
		assertEquals("imported bottle of perfume", receipt.getItems().get(0).getName());
		assertEquals(Double.valueOf("32.19"), Double.valueOf(receipt.getItems().get(0).getPrice().doubleValue()));
		assertEquals("bottle of perfume", receipt.getItems().get(1).getName());
		assertEquals(Double.valueOf("20.89"), Double.valueOf(receipt.getItems().get(1).getPrice().doubleValue()));
		assertEquals("packet of headache pills", receipt.getItems().get(2).getName());
		assertEquals(Double.valueOf("9.75"), Double.valueOf(receipt.getItems().get(2).getPrice().doubleValue()));
		assertEquals("box of imported chocolates", receipt.getItems().get(3).getName());
		assertEquals(Double.valueOf("11.85"), Double.valueOf(receipt.getItems().get(3).getPrice().doubleValue()));
		
		assertEquals(Double.valueOf("6.70"), Double.valueOf(receipt.getTaxes()));
		assertEquals(Double.valueOf("74.68"), Double.valueOf(receipt.getTotalPrice())); 
	}
	
	private List<ItemVO> generateInput1() {
		List<ItemVO> elemList = new ArrayList<>();
		ItemVO item = new ItemVO();
		item.setKind(KindEnum.BOOK);
		item.setPrice(new BigDecimal("12.49"));
		item.setName("book");
		elemList.add(item);
		item = new ItemVO();
		item.setKind(KindEnum.OTHER);
		item.setPrice(new BigDecimal("14.99"));
		item.setName("music CD");
		elemList.add(item);
		item = new ItemVO();
		item.setKind(KindEnum.FOOD);
		item.setPrice(new BigDecimal("0.85"));
		item.setName("chocolate bar");
		elemList.add(item);
		return elemList;
	}
	
	private List<ItemVO> generateInput2() {
		List<ItemVO> elemList = new ArrayList<>();
		ItemVO item = new ItemVO();
		item.setKind(KindEnum.FOOD);
		item.setPrice(new BigDecimal("10.00"));
		item.setName("imported box of chocolates");
		elemList.add(item);
		item = new ItemVO();
		item.setKind(KindEnum.OTHER);
		item.setPrice(new BigDecimal("47.50"));
		item.setName("imported bottle of perfume");
		elemList.add(item);
		return elemList;
	}
	
	private List<ItemVO> generateInput3() {
		List<ItemVO> elemList = new ArrayList<>();
		ItemVO item = new ItemVO();
		item.setKind(KindEnum.OTHER);
		item.setPrice(new BigDecimal("27.99"));
		item.setName("imported bottle of perfume");
		elemList.add(item);
		item = new ItemVO();
		item.setKind(KindEnum.OTHER);
		item.setPrice(new BigDecimal("18.99"));
		item.setName("bottle of perfume");
		elemList.add(item);
		item = new ItemVO();
		item.setKind(KindEnum.MEDICAL_PRODUCT);
		item.setPrice(new BigDecimal("9.75"));
		item.setName("packet of headache pills");
		elemList.add(item);
		item = new ItemVO();
		item.setKind(KindEnum.FOOD);
		item.setPrice(new BigDecimal("11.25"));
		item.setName("box of imported chocolates");
		elemList.add(item);
		return elemList;
	}
}
