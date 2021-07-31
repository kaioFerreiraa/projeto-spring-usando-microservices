package com.kaioferreira.pagamento.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import com.kaioferreira.pagamento.data.vo.VendaVO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="venda")
@Getter @ Setter
@NoArgsConstructor @AllArgsConstructor
@ToString @EqualsAndHashCode
public class Venda implements Serializable {
	
	private static final long serialVersionUID = -6464857081743803576L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Column(name="data", nullable=false)
	private Date data;
	
	@OneToMany(mappedBy="venda", fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
	private List<ProdutoVenda> produtos;
	
	@Column(name="valor_total", nullable=false, length=10)
	private Double valorTotal;
	
	public static Venda create(VendaVO vendaVO) {
		return new ModelMapper().map(vendaVO, Venda.class);
	}
	
}
