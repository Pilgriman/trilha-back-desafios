package trilha.back.financys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class FinancysApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancysApplication.class, args);
//comentei os testes feitos do desafio 2

		/*Category category = new Category(4,"Salário","Recebimento de salário");
		Category category1 = new Category();

		category1.setId(3);
		category1.setName("Saúde");
		category1.setDescription("Plano de saúde");

		System.out.println(category.toString());
		System.out.println(category1.toString());


		Entry entry = new Entry(3, "Salário na empresa X", "Adiatamento quinzenal",
				"revenue", "4405,49","15/09/2021",true,4);
		Entry entry1 = new Entry();

		entry1.setId(1);
		entry1.setName("Salário na empresa y");
		entry1.setDescription("mensal");
		entry1.setType("revenue");
		entry1.setAmount("4405,49");
		entry1.setDate("16/09/2021");
		entry1.setPaid(true);
		entry1.setCategoryId(2);

		System.out.println(entry.toString());
		System.out.println(entry1.toString());



*/

	}

}
