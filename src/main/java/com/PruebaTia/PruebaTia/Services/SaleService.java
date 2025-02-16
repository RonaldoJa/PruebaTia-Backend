package com.PruebaTia.PruebaTia.Services;

import com.PruebaTia.PruebaTia.Entity.DTO.DetailSaleDTO;
import com.PruebaTia.PruebaTia.Entity.DTO.LocalSaleDTO;
import com.PruebaTia.PruebaTia.Entity.DTO.ProductSaleDTO;
import com.PruebaTia.PruebaTia.Entity.DTO.SaleDTO;
import com.PruebaTia.PruebaTia.Entity.DetailSale;
import com.PruebaTia.PruebaTia.Entity.Product;
import com.PruebaTia.PruebaTia.Entity.Sale;
import com.PruebaTia.PruebaTia.Entity.Stock;
import com.PruebaTia.PruebaTia.Repository.ProductRepository;
import com.PruebaTia.PruebaTia.Repository.SaleRepository;
import com.PruebaTia.PruebaTia.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProductRepository productRepository;

    public Sale crearVenta(Sale venta) {
        if (venta.getDetalles() == null) {
            venta.setDetalles(new HashSet<>());
        }

        BigDecimal totalVenta = BigDecimal.ZERO;

        for (DetailSale detail : venta.getDetalles()) {
            Optional<Product> productoOptional = productRepository.findById(detail.getProducto().getId());
            if (productoOptional.isPresent()) {
                Product producto = productoOptional.get();
                BigDecimal precioUnitario = producto.getPrecioBase();
                BigDecimal subtotal = precioUnitario.multiply(new BigDecimal(detail.getCantidad()));
                detail.setSubtotal(subtotal);

                totalVenta = totalVenta.add(subtotal);
            } else {
                throw new RuntimeException("Producto no encontrado con id: " + detail.getProducto().getId());
            }

            Optional<Stock> stock = stockRepository.findByProductoAndLocal(detail.getProducto(), venta.getLocal());

            if (stock.isPresent()) {
                Stock stockActual = stock.get();
                stockActual.setCantidadDisponible(stockActual.getCantidadDisponible() - detail.getCantidad());
                stockActual.setFechaActualizacion(LocalDateTime.now());
                stockRepository.save(stockActual);
            }
        }

        venta.setTotal(totalVenta);

        return saleRepository.save(venta);
    }


    public Sale obtenerVenta(Long id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    public List<SaleDTO> listarVentas() {
        return saleRepository.findAll().stream().map(this::mapToSaleDTO)
                .collect(Collectors.toList());
    }

    private SaleDTO mapToSaleDTO(Sale sale) {
        SaleDTO dto = new SaleDTO();
        dto.setSaleId(sale.getId());
        dto.setTotal(sale.getTotal());

        if (sale.getLocal() != null) {
            LocalSaleDTO localDTO = new LocalSaleDTO();
            localDTO.setLocalId(sale.getLocal().getId());
            localDTO.setNombre(sale.getLocal().getNombre());
            dto.setLocal(localDTO);
        }

        if (sale.getDetalles() != null) {
            List<DetailSaleDTO> detalleDTOs = sale.getDetalles().stream().map(this::mapToDetailSaleDTO)
                    .collect(Collectors.toList());
            dto.setDetalles(detalleDTOs);
        }
        return dto;
    }

    private DetailSaleDTO mapToDetailSaleDTO(DetailSale detail) {
        DetailSaleDTO detailDTO = new DetailSaleDTO();
        detailDTO.setDetailId(detail.getId());

        if (detail.getProducto() != null) {
            ProductSaleDTO productDTO = new ProductSaleDTO();
            productDTO.setProductId(detail.getProducto().getId());
            productDTO.setNombre(detail.getProducto().getNombre());
            productDTO.setCantidad(detail.getCantidad());
            detailDTO.setProduct(productDTO);
        }
        return detailDTO;
    }
}
