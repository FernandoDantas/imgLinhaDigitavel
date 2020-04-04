package domain;

import auxiliar.ImageNumbers;
import auxiliar.exceptions.ConvertToImageException;

public class DebitoDomain {

	private String dbtCodigoBarras = "23797810900000036003211090000005086300253820";
	                                  

	public String getDbtLinhaDigitavel() throws ConvertToImageException {

		StringBuffer Linha = new StringBuffer();
		if (dbtCodigoBarras.trim().length() > 0) {
			Linha.append(dbtCodigoBarras.substring(0, 4));
			Linha.append(dbtCodigoBarras.substring(19, 44));
			Linha.append(dbtCodigoBarras.substring(4, 19));
			Linha.insert(9, dv(dbtCodigoBarras.substring(0, 4) + dbtCodigoBarras.substring(19, 24)));
			Linha.insert(20, dv(dbtCodigoBarras.substring(24, 34)));
			Linha.insert(31, dv(dbtCodigoBarras.substring(34, 44)));
			Linha.insert(5, ".");
			Linha.insert(11, "&nbsp; &nbsp;");
			Linha.insert(29, ".");
			Linha.insert(36, "&nbsp; &nbsp;");
			Linha.insert(54, ".");
			Linha.insert(61, "&nbsp; &nbsp;");
			Linha.insert(75, "&nbsp; &nbsp;");

		}

		// return dbtCodigoBarras;
		// return Linha.toString(); original

		ImageNumbers in = new ImageNumbers(Linha.toString().replace("&nbsp;", " "));
		System.out.println(Linha.toString());

		return in.getImageURL();

	}
	
	private String dv(String Codigo) {
		int soma = 0;
		int tamanho = Codigo.length();
		if (tamanho % 2 == 1) {
			Codigo = "0" + Codigo;
			tamanho = Codigo.length();
		}
		for (int i = tamanho, j = 0; j != tamanho; j++, i--) {
			if (i % 2 != 1) {
				soma
					+= somaDigitosModuloDez(
						((new Integer(Codigo.substring(i - 1, i))).intValue())
							* 2);
			} else {
				soma += (new Integer(Codigo.substring(i - 1, i))).intValue();
			}
		}
		if (moduloDez(soma) == 0)
			return "0";
		else
			return new Integer(10 - moduloDez(soma)).toString();
	} //dv

	private int moduloDez(int numero) {
		return numero % 10;
	}

	private int somaDigitosModuloDez(int numero) {
		return (numero >= 10) ? moduloDez(numero) + 1 : moduloDez(numero);
	}
	

	public String getDbtCodigoBarras() {
		return dbtCodigoBarras;
	}

	public void setDbtCodigoBarras(String dbtCodigoBarras) {
		this.dbtCodigoBarras = dbtCodigoBarras;
	}

}
