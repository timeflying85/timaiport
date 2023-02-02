package be.technifutur.java.timairport.controller;

import be.technifutur.java.timairport.model.dto.PlaneDTO;
import be.technifutur.java.timairport.model.form.PlaneInsertForm;
import be.technifutur.java.timairport.service.PlaneService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/plane")
public class PlaneController {

    private final PlaneService planeService;

    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add")
    public void create(@RequestBody @Valid PlaneInsertForm form){

        planeService.create( form );

    }

    @GetMapping("/{id:[0-9]+}")
    public PlaneDTO getOne(@PathVariable long id){

        return planeService.getOne(id);

    }


    @GetMapping("/all")
    public List<PlaneDTO> getAll(){

        return planeService.getAll();

    }


    @PatchMapping(value = "/{id:[0-9]+}/update", params = "companyId")
    public void updateCompany(@PathVariable("id") long idPlane, @RequestParam long companyId){

        planeService.updateCompany(idPlane,companyId);

    }



    @PatchMapping(value = "/{id:[0-9]+}/update", params = "maintenance")
    public void updateMaintenance(@PathVariable("id") long idPlane, @RequestParam boolean maintenance){

        planeService.updateMaintenance(idPlane,maintenance);

    }


    @PatchMapping("/{id:[0-9]+}/update/both")
    public void update(@PathVariable long id, @RequestParam Map<String, String> params){

        Map<String, Object> mapValues = new HashMap<>();

        if (params.containsKey("companyId"))
            mapValues.put("companyId", Long.parseLong(params.get("companyId")));

        if (params.containsKey("inMaintenance"))
            mapValues.put("inMaintenance", Boolean.parseBoolean(params.get("inMaintenance")));

        planeService.update( id, mapValues );
    }

    @GetMapping({"/{id:[0-9]+}","/{id:[0-9]+}/delete"} )
    public void delete(@PathVariable long id){

        planeService.delete(id);


    }




}
