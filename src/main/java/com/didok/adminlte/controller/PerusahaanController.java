package com.didok.adminlte.controller;

import com.didok.adminlte.model.Perusahaans;
import com.didok.adminlte.service.PerusahaansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("perusahaans")
public class PerusahaanController {

    private PerusahaansService perusahaanService;

    @Autowired
    public void setPerusahaanService(PerusahaansService perusahaanService) {
        this.perusahaanService = perusahaanService;
    }

    @GetMapping
    public String index() {
        return "redirect:/perusahaans/1";
    }

    @GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<Perusahaans> page = perusahaanService.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "perusahaans/list";

    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("perusahaan", new Perusahaans());
        return "perusahaans/form";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        model.addAttribute("perusahaan", perusahaanService.get(id));
        return "perusahaans/form";

    }

    @PostMapping(value = "/save")
    public String save(Perusahaans perusahaan, final RedirectAttributes ra) {

        Perusahaans save = perusahaanService.save(perusahaan);
        ra.addFlashAttribute("successFlash", "Cliente foi salvo com sucesso.");
        return "redirect:/perusahaans";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        perusahaanService.delete(id);
        return "redirect:/perusahaans";

    }

}
