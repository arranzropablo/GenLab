package com.genlab.serverapplication.controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.genlab.serverapplication.models.CTResult;
import com.genlab.serverapplication.models.CalculationTool;
import com.genlab.serverapplication.models.CalculationToolsMapping;
import com.genlab.serverapplication.models.SectionsMapping;
import com.genlab.serverapplication.services.ctservice.Epistasias.CTEpistasias;
import com.genlab.serverapplication.services.ctservice.Linkage.CTLinkage;
import com.genlab.serverapplication.services.ctservice.Onelocus.CTOneLocus;
import com.genlab.serverapplication.services.ctservice.Polyhybrid.CTPolyHybrid;
import com.genlab.serverapplication.services.ctservice.Twoloci.CTTwoLoci;

@Controller
@RequestMapping("/calculationtools")
public class CalculationToolsController {

    @Autowired
    private CTTwoLoci twoLociService;

    @Autowired
    private CTOneLocus oneLocusService;

    @Autowired
    private CTLinkage linkageService;

    @Autowired
    private CTPolyHybrid polyhybridService;

    @Autowired
    private CTEpistasias epistasiasService;

	@GetMapping("")
	public String getCalculationTool(Model model, HttpSession session) {
		return "redirect:/calculationtools/"+ String.valueOf(((SectionsMapping)session.getAttribute("currentSection")).getId()) +"0";
	}
	
	@GetMapping("/{id}")
	public String getCalculationTool(@PathVariable("id") String id, Model model, HttpSession session) {
		List<CalculationTool> calc_list = new ArrayList<>();

		for (CalculationToolsMapping cp : CalculationToolsMapping.values()){
			if(cp.getSectionId() == ((SectionsMapping)session.getAttribute("currentSection")).getId()){
				calc_list.add(CalculationTool.builder().id(String.valueOf(cp.getSectionId()) + String.valueOf(cp.getId())).name(cp.getValue()).build());
			}
		}
		model.addAttribute("calculationTools", calc_list);

		model.addAttribute("calcToShow", id);

		return "calculationTools";
	}

    @PostMapping("/result")
    public String getResult(@RequestParam("CTid") String CTid, @RequestBody HashMap<String, Double> values, Model model, HttpSession session, RedirectAttributes redirect){
    		CTResult result = getCalcResult(CTid, values);
    		
    		//Formateamos los esperados y los resultados para que tengan 2 decimales
    		DecimalFormat df2 = new DecimalFormat(".##");
    		
    		if (result.getExpectedValues() != null) {
        		result.getExpectedValues().keySet().stream().forEach(key ->{
        			result.getExpectedValues().put(key, Double.parseDouble(df2.format(result.getExpectedValues().get(key))));
        		});
    		}

    		if(result.getResultValues() != null) {
        		result.getResultValues().keySet().stream().forEach(key ->{
        			if(Double.isNaN(result.getResultValues().get(key))) {
        				result.getResultValues().put(key, null);
        			} else {
            			result.getResultValues().put(key, Double.parseDouble(df2.format(result.getResultValues().get(key))));
        			}
        		});
    		}
        redirect.addFlashAttribute("result", result);
        return "redirect:/calculationtools/" + CTid;
    }
    
    public CTResult getCalcResult(String CTid, HashMap<String, Double> values) {
		CTResult result = CTResult.builder().feedbackMessage("Error").cleanInputs(true).build();
        switch(Integer.parseInt(String.valueOf(CTid.toCharArray()[0]))){
            case 0:
                switch (Integer.parseInt(String.valueOf(CTid.toCharArray()[1]))){
                    case 0:
                    
                        result = twoLociService.testcross(values.get("AB").intValue(),
                                                    values.get("Ab").intValue(),
                                                    values.get("aB").intValue(),
                                                    values.get("ab").intValue());
                        break;
                    case 1:
                        result = twoLociService.f2Dominance(values.get("AB").intValue(),
                                                    values.get("Ab").intValue(),
                                                    values.get("aB").intValue(),
                                                    values.get("ab").intValue());
                        break;
                    case 2:
                        result = twoLociService.f2coDominance(values.get("A1A1B1B1").intValue(),
                                                        values.get("A1A1B1B2").intValue(),
                                                        values.get("A1A1B2B2").intValue(),
                                                        values.get("A1A2B1B1").intValue(),
                                                        values.get("A1A2B1B2").intValue(),
                                                        values.get("A1A2B2B2").intValue(),
                                                        values.get("A2A2B1B1").intValue(),
                                                        values.get("A2A2B1B2").intValue(),
                                                        values.get("A2A2B2B2").intValue());
                        break;
                    case 3:
                        result = twoLociService.f2coDom2dom(values.get("A1A1B").intValue(),
                                                    values.get("A1A2B").intValue(),
                                                    values.get("A2A2B").intValue(),
                                                    values.get("A1A1b").intValue(),
                                                    values.get("A1A2b").intValue(),
                                                    values.get("A2A2b").intValue());
                        break;
                    case 4:
                        result = twoLociService.f2coDom4dom(values.get("A1A3B").intValue(),
                                                    values.get("A1A3b").intValue(),
                                                    values.get("A1A4B").intValue(),
                                                    values.get("A1A4b").intValue(),
                                                    values.get("A2A3B").intValue(),
                                                    values.get("A2A3b").intValue(),
                                                    values.get("A2A4B").intValue(),
                                                    values.get("A2A4b").intValue());
                        break;
                    case 5:
                        result = twoLociService.f2TestcrossDom(values.get("AB").intValue(),
                                                        values.get("Ab").intValue(),
                                                        values.get("aB").intValue(),
                                                        values.get("ab").intValue());
                        break;
                    case 6:
                        result = twoLociService.f2Testcross2Dom(values.get("A1A1B").intValue(),
                                                        values.get("A1A2B").intValue(),
                                                        values.get("A2A2B").intValue(),
                                                        values.get("A1A1b").intValue(),
                                                        values.get("A1A2b").intValue(),
                                                        values.get("A2A2b").intValue());
                        break;
                    case 7:
                        result = twoLociService.f2Testcross4Dom(values.get("A1A3B").intValue(),
                                                        values.get("A1A3b").intValue(),
                                                        values.get("A1A4B").intValue(),
                                                        values.get("A1A4b").intValue(),
                                                        values.get("A2A3B").intValue(),
                                                        values.get("A2A3b").intValue(),
                                                        values.get("A2A4B").intValue(),
                                                        values.get("A2A4b").intValue());
                        break;
                }
                break;
            case 1:
                switch (Integer.parseInt(String.valueOf(CTid.toCharArray()[1]))){
                    case 0:
                        result = oneLocusService.testcross(values.get("A").intValue(),
                                                    values.get("a").intValue());
                        break;
                    case 1:
                        result = oneLocusService.f2Dominance(values.get("A").intValue(),
                                                    values.get("a").intValue());
                        break;
                    case 2:
                        result = oneLocusService.f2CoDominance(values.get("A1A1").intValue(),
                                                        values.get("A1A2").intValue(),
                                                        values.get("A2A2").intValue());
                        break;
                    case 3:
                        result = oneLocusService.coDominance3Alleles(values.get("A1A1").intValue(),
                                                            values.get("A1A2").intValue(),
                                                            values.get("A1A3").intValue(),
                                                            values.get("A2A3").intValue());
                        break;
                    case 4:
                        result = oneLocusService.coDominance4Alleles(values.get("A1A3").intValue(),
                                                            values.get("A1A4").intValue(),
                                                            values.get("A2A3").intValue(),
                                                            values.get("A2A4").intValue());
                        break;
                    case 5:
                        result = oneLocusService.lethalGenes(values.get("AA").intValue(),
                                                    values.get("Aa").intValue());
                        break;
                }
                break;
            case 2:
                switch (Integer.parseInt(String.valueOf(CTid.toCharArray()[1]))){
                    case 0:
                        result = linkageService.testcross2Loci(values.get("AB").intValue(),
                                                        values.get("Ab").intValue(),
                                                        values.get("aB").intValue(),
                                                        values.get("ab").intValue());
                        break;
                    case 1:
                        result = linkageService.f22LociDominance(values.get("AB").intValue(),
                                                        values.get("Ab").intValue(),
                                                        values.get("aB").intValue(),
                                                        values.get("ab").intValue());
                        break;
                    case 2:
                        result = linkageService.f22LociCodominance(values.get("A1A1B1B1").intValue(),
                                                            values.get("A1A1B1B2").intValue(),
                                                            values.get("A1A1B2B2").intValue(),
                                                            values.get("A1A2B1B1").intValue(),
                                                            values.get("A1A2B1B2").intValue(),
                                                            values.get("A1A2B2B2").intValue(),
                                                            values.get("A2A2B1B1").intValue(),
                                                            values.get("A2A2B1B2").intValue(),
                                                            values.get("A2A2B2B2").intValue());
                        break;
                    case 3:
                        result = linkageService.testcross3Loci(values.get("ABC").intValue(),
                                                        values.get("abc").intValue(),
                                                        values.get("ABc").intValue(),
                                                        values.get("abC").intValue(),
                                                        values.get("aBC").intValue(),
                                                        values.get("Abc").intValue(),
                                                        values.get("AbC").intValue(),
                                                        values.get("aBc").intValue());
                        break;
                    case 4:
                        result = linkageService.testcrossDM(values.get("r1"),
                                                    values.get("tOs"));
                        break;
                    case 5:
                        result = linkageService.dominanceDM(values.get("r1"),
                                                    values.get("tOs"));
                        break;
                    case 6:
                        result = linkageService.codominanceDM(values.get("r1"),
                                                        values.get("tOs"));
                        break;
                    case 7:
                        result = linkageService.tresLociDM(values.get("r1"),
                                                        values.get("r2"),
                                                        values.get("cOc"),
                                                        values.get("tOs"));
                        break;
                }
                break;
            case 3:
                switch (Integer.parseInt(String.valueOf(CTid.toCharArray()[1]))){
                    case 0:
                        result = epistasiasService.whithoutModif(values.get("AB").intValue(),
                                values.get("aB").intValue(),
                                values.get("Ab").intValue(),
                                values.get("ab").intValue());
                        break;
                    case 1:
                        result = epistasiasService.singleRecessive(values.get("AB").intValue(),
                                values.get("Ab").intValue(),
                                values.get("aBab").intValue());
                        break;
                    case 2:
                        result = epistasiasService.singleDominant(values.get("aB").intValue(),
                                values.get("ab").intValue(),
                                values.get("ABAb").intValue());
                        break;
                    case 3:
                        result = epistasiasService.singleAdditive(values.get("AB").intValue(),
                                values.get("ab").intValue(),
                                values.get("AbaB").intValue());
                        break;
                    case 4:
                        result = epistasiasService.doubleRecessive(values.get("AB").intValue(),
                                values.get("AbaBab").intValue());
                        break;
                    case 5:
                        result = epistasiasService.doubleDominant(values.get("ab").intValue(),
                                values.get("ABAbaB").intValue());
                        break;
                    case 6:
                        result = epistasiasService.doubleDominantRecessive(values.get("aB").intValue(),
                                values.get("ABAbab").intValue());
                        break;
                    case 7:
                        result = epistasiasService.segregation6334(values.get("6").intValue(),
                                values.get("3a").intValue(),
                                values.get("3b").intValue(),
                                values.get("4").intValue());
                        break;
                    case 8:
                        result = epistasiasService.segregation1033(values.get("10").intValue(),
                                values.get("3a").intValue(),
                                values.get("3b").intValue());
                        break;
                }
                break;
            case 4:
                switch (Integer.parseInt(String.valueOf(CTid.toCharArray()[1]))){
                    case 0:
                        result = polyhybridService.polyhybrid(values.get("n"),
                                                        values.get("h"),
                                                        values.get("d"),
                                                        values.get("r"),
                                                        values.get("D"),
                                                        values.get("R"),
                                                        values.get("T"));
                        break;
                    case 1:
                        result = polyhybridService.multiplealleles(values.get("locus1"),
                                                            values.get("locus2"),
                                                            values.get("locus3"),
                                                            values.get("locus4"),
                                                            values.get("locus5"));
                        break;
                }
                break;
        }
        
        return result;
	}

}
