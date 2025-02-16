package com.PruebaTia.PruebaTia.Services;

import com.PruebaTia.PruebaTia.Entity.DTO.GetStockIdDTO;
import com.PruebaTia.PruebaTia.Entity.DTO.LocalSaleDTO;
import com.PruebaTia.PruebaTia.Entity.DTO.ProductAssign;
import com.PruebaTia.PruebaTia.Entity.DTO.ProductSaleDTO;
import com.PruebaTia.PruebaTia.Entity.Local;
import com.PruebaTia.PruebaTia.Entity.Product;
import com.PruebaTia.PruebaTia.Entity.Stock;
import com.PruebaTia.PruebaTia.Repository.LocalRepository;
import com.PruebaTia.PruebaTia.Repository.ProductRepository;
import com.PruebaTia.PruebaTia.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private LocalRepository localRepository;

    public ProductAssign crearStock(ProductAssign productAssign) {
        Optional<Product> product = productRepository.findById(productAssign.getProductoId());
        Optional<Local> local = localRepository.findById(productAssign.getLocalId());

        if (product.isEmpty() || local.isEmpty()) {
            return null;
        }

        Optional<Stock> stockExistente = stockRepository.findByProductoAndLocal(product.get(), local.get());

        Stock stock;

        if (stockExistente.isPresent()) {
            stock = stockExistente.get();
            stock.setCantidadDisponible(stock.getCantidadDisponible() + productAssign.getCantidadDisponible());
            stock.setUmbralMinimo(productAssign.getUmbralMinimo());
            stock.setFechaActualizacion(LocalDateTime.now());
        } else {
            stock = new Stock();
            stock.setProducto(product.get());
            stock.setLocal(local.get());
            stock.setCantidadDisponible(productAssign.getCantidadDisponible());
            stock.setUmbralMinimo(productAssign.getUmbralMinimo());
            stock.setFechaActualizacion(LocalDateTime.now());
        }
        stockRepository.save(stock);

        ProductAssign productAssignFinal = new ProductAssign();
        productAssignFinal.setProductoId(productAssign.getProductoId());
        productAssignFinal.setLocalId(productAssign.getLocalId());
        productAssignFinal.setCantidadDisponible(stock.getCantidadDisponible());
        productAssignFinal.setUmbralMinimo(productAssign.getUmbralMinimo());

        return productAssignFinal;

    }

    public GetStockIdDTO obtenerStock(Long localId, Long productoId) {
        Stock stock = stockRepository.findByProductoIdAndLocalId(productoId, localId);
        if (stock != null) {
            return mapToStockDTO(stock);
        } else {
            return null;
        }
    }

    public Stock actualizarStock(Long localId, Long productoId, Integer cantidad) {
        Stock stock = stockRepository.findByProductoIdAndLocalId(productoId, localId);
        stock.setCantidadDisponible(stock.getCantidadDisponible() + cantidad);
        return stockRepository.save(stock);
    }

    public List<GetStockIdDTO> listarStocks() {
        return stockRepository.findAll().stream()
                .map(this::mapToStockDTO)
                .collect(Collectors.toList());
    }

    private GetStockIdDTO mapToStockDTO(Stock stock) {
        GetStockIdDTO dto = new GetStockIdDTO();
        dto.setId(stock.getId());
        dto.setCantidadDisponible(stock.getCantidadDisponible());
        dto.setUmbralMinimo(stock.getUmbralMinimo());
        dto.setFechaActualizacion(stock.getFechaActualizacion());

        if (stock.getLocal() != null) {
            LocalSaleDTO localDTO = new LocalSaleDTO();
            localDTO.setLocalId(stock.getLocal().getId());
            localDTO.setNombre(stock.getLocal().getNombre());
            dto.setLocal(localDTO);
        }

        if (stock.getProducto() != null) {
            ProductSaleDTO productDTO = new ProductSaleDTO();
            productDTO.setProductId(stock.getProducto().getId());
            productDTO.setNombre(stock.getProducto().getNombre());
            dto.setProducto(productDTO);
        }
        return dto;
    }
}