package com.didok.adminlte.controller;

import com.didok.adminlte.service.*;
import com.didok.adminlte.util.ExcelGenerator;
import com.didok.adminlte.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("transaksis")
public class TransaksiController {

    private TransaksisService transaksiService;
    private BarangsService barangService;
    private PerusahaansService perusahaanService;

    @Autowired
    public void setTransaksiService(TransaksisService transaksiService, BarangsService barangService,
            PerusahaansService perusahaanService) {
        this.transaksiService = transaksiService;
        this.barangService = barangService;
        this.perusahaanService = perusahaanService;
    }

    @GetMapping
    public String index() {
        return "redirect:/transaksis/1";
    }

    @GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<Transaksis> page = transaksiService.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "transaksis/list";

    }

    @GetMapping("/add")
    public String add(Model model) {

        Page<Barangs> listBarangs = barangService.getList(1);
        Page<Perusahaans> listPerusahaans = perusahaanService.getList(1);

        model.addAttribute("listBarang", listBarangs.filter(o -> o.getStock() > 0));
        model.addAttribute("listPerusahaan", listPerusahaans);
        model.addAttribute("transaksi", new Transaksis());
        return "transaksis/form";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        Page<Barangs> listBarangs = barangService.getList(10);
        Page<Perusahaans> listPerusahaans = perusahaanService.getList(10);

        model.addAttribute("listBarang", listBarangs);
        model.addAttribute("listPerusahaan", listPerusahaans);
        model.addAttribute("transaksi", transaksiService.get(id));
        return "transaksis/form";

    }

    @PostMapping(value = "/save")
    public String save(Transaksis transaksi, final RedirectAttributes ra) {

        Barangs barang = barangService.get(transaksi.getId_barang());
        if (barang != null){
            transaksi.setGrand_total(transaksi.getQty() * barang.getHarga());
        }
        Transaksis save = transaksiService.save(transaksi);

        if(save != null){
            Double sisa = barang.getStock() - transaksi.getQty();
            barang.setStock(sisa);
            barangService.update(barang);
        }
        ra.addFlashAttribute("successFlash", "Cliente foi salvo com sucesso.");
        return "redirect:/transaksis";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        transaksiService.delete(id);
        return "redirect:/transaksis";

    }

    @GetMapping("/excel")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=transaksi" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        Page<Transaksis> listOfTransaksis = transaksiService.getList(1);
        ExcelGenerator generator = new ExcelGenerator(listOfTransaksis.toList());
        generator.generateExcelFile(response);
    }
  


}
