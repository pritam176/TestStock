/**
 * 
 */
package com.mmt.controller;

import java.security.Principal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mmt.Entity.GSTSlabEntity;
import com.mmt.command.GSTSlabCommand;
import com.mmt.dto.GSTDTO;
import com.mmt.service.GSTService;

/**
 * @author pkumar
 *
 */
@Controller
@RequestMapping("gst")
public class GSTController {

	private static final Logger logger = LoggerFactory.getLogger(GSTController.class);

	@Autowired
	private GSTService gSTService;

	@PostMapping(value = "/addgstslab.html")
	public String addGSTPost(@ModelAttribute("gstCommandForm") GSTSlabCommand gSTSlabCommand, Model model,
			Principal principal) {
		logger.debug("Enter In to addgstslab post");
		System.out.println(gSTSlabCommand.getName());
		// model.addAttribute("purchaseCommandForm", purchaseCommandForm);

		gSTService.saveGstSlab(gSTSlabCommand);

		return "redirect:/mmt/thankyou.html?msg=Data Saved Successfully";

	}

	@PostMapping(value = "/updategstslab.html")
	public String updateGSTPost(@ModelAttribute("gstCommandForm") GSTSlabCommand gSTSlabCommand, Model model,
			Principal principal) {
		logger.debug("Enter In to addgstslab post");
		System.out.println(gSTSlabCommand.getName());
		// model.addAttribute("purchaseCommandForm", purchaseCommandForm);

		gSTService.updateGstSlab(gSTSlabCommand);

		return "redirect:/mmt/thankyou.html?msg=Data Updated Successfully";

	}

	@GetMapping(value = "/gstreport.html")
	public String gstreport(Model model, Principal principal) {
		logger.debug("Enter In to gst Report Get");

		model.addAttribute("gstSearchcommand", new GSTSlabCommand());
		List<GSTSlabEntity> getAllGstSlabList = gSTService.getAllGstSlab();

		Map<Long, String> gstslabmap = new HashMap<>();
		Iterator<GSTSlabEntity> gstit = getAllGstSlabList.iterator();
		while (gstit.hasNext()) {
			GSTSlabEntity pe = gstit.next();
			gstslabmap.put(pe.getSlab_id(), pe.getName());
		}

		model.addAttribute("gstslabmap", gstslabmap);

		return "gstreport.tiles";

	}

	@PostMapping(value = "/gstreport.html")
	public String gstPostreport(@ModelAttribute("gstCommandForm") GSTSlabCommand gSTSlabCommand, Model model,
			Principal principal) {
		logger.debug("Enter In to gst Report Get");

		model.addAttribute("gstSearchcommand", gSTSlabCommand);
		List<GSTSlabEntity> getAllGstSlabList = gSTService.getAllGstSlab();

		Map<Long, String> gstslabmap = new HashMap<>();
		Iterator<GSTSlabEntity> gstit = getAllGstSlabList.iterator();
		while (gstit.hasNext()) {
			GSTSlabEntity pe = gstit.next();
			gstslabmap.put(pe.getSlab_id(), pe.getName());
		}

		model.addAttribute("gstslabmap", gstslabmap);

		try {
			List<GSTDTO> listDto = gSTService.getAllGstSlabData(gSTSlabCommand);
			model.addAttribute("listDto", listDto);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "gstreport.tiles";

	}
	@GetMapping(value = "/isAvalblebySlabName.html")
	public @ResponseBody String findSlabName(@RequestParam(value = "name", required = false) String name){
		
		return gSTService.isAvalblebySlabName(name)+"";
		
	}
	
	@GetMapping(value = "/isAvalblebyTaxRate.html")
	public @ResponseBody String findTaxRate(@RequestParam(value = "taxrate", required = false) String taxrate){
		
		return gSTService.isAvalblebyTaxRate(taxrate)+"";
		
	}

}
