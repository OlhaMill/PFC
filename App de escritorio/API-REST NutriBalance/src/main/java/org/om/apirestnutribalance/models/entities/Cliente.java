package org.om.apirestnutribalance.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "nombre", length = 100)
    private String nombre;

    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;

    @Size(max = 15)
    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nutricionista_id")
    private Nutricionista nutricionista;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "valor_nutricional_rec")
    private ValorNutricional valorNutricionalRec;

    @Column(name = "fotos")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> fotos;

    @Size(max = 20)
    @NotNull
    @Column(name = "genero", nullable = false, length = 20)
    private String genero;

    @Column(name = "\"valoración\"")
    private Double valoracion;

    @OneToMany(mappedBy = "cliente")
    private Set<ClienteDieta> listaDietas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cliente")
    private Set<Consulta> progreso = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Nutricionista getNutricionista() {
        return nutricionista;
    }

    public void setNutricionista(Nutricionista nutricionista) {
        this.nutricionista = nutricionista;
    }

    public ValorNutricional getValorNutricionalRec() {
        return valorNutricionalRec;
    }

    public void setValorNutricionalRec(ValorNutricional valorNutricionalRec) {
        this.valorNutricionalRec = valorNutricionalRec;
    }

    public Map<String, Object> getFotos() {
        return fotos;
    }

    public void setFotos(Map<String, Object> fotos) {
        this.fotos = fotos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Double getValoracion() {
        return valoracion;
    }

    public void setValoracion(Double valoracion) {
        this.valoracion = valoracion;
    }

    public Set<ClienteDieta> getListaDietas() {
        return listaDietas;
    }

    public void setListaDietas(Set<ClienteDieta> listaDietas) {
        this.listaDietas = listaDietas;
    }

    public Set<Consulta> getProgreso() {
        return progreso;
    }

    public void setProgreso(Set<Consulta> progreso) {
        this.progreso = progreso;
    }

    @JsonProperty("creadoPorId")
    public Integer getCreadoPorId() {
        return nutricionista != null ? nutricionista.getId() : null;
    }
}