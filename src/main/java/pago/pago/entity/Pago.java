package pago.pago.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "pagos")
public class Pago {

    @Id
    private String id;

    private String compraId;        
    private Double monto;
    private MedioPago medio;
    private EstadoPago estado;
    private String referenciaPasarela;  
    private String urlRedireccion;    

    @CreatedDate
    private Instant fechaCreacion;

    public Pago() {}

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCompraId() { return compraId; }
    public void setCompraId(String compraId) { this.compraId = compraId; }
    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }
    public MedioPago getMedio() { return medio; }
    public void setMedio(MedioPago medio) { this.medio = medio; }
    public EstadoPago getEstado() { return estado; }
    public void setEstado(EstadoPago estado) { this.estado = estado; }
    public String getReferenciaPasarela() { return referenciaPasarela; }
    public void setReferenciaPasarela(String referenciaPasarela) { this.referenciaPasarela = referenciaPasarela; }
    public String getUrlRedireccion() { return urlRedireccion; }
    public void setUrlRedireccion(String urlRedireccion) { this.urlRedireccion = urlRedireccion; }
    public Instant getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Instant fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
