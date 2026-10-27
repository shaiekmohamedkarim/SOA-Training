package business;

import entities.Module;
import entities.TypeModule;
import entities.UniteEnseignement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModuleBusiness {
	private static final List<Module> modules = new ArrayList<Module>();
	private final UniteEnseignementBusiness uniteEnseignementBusiness;

	static {
		UniteEnseignementBusiness ueBusiness = new UniteEnseignementBusiness();
		modules.add(new Module("M101", "Algorithmique", 3, 21, TypeModule.PROFESSIONNEL,
				ueBusiness.getUEByCode(1)));
		modules.add(new Module("M102", "Bases de donnees", 2, 14, TypeModule.TRANSVERSAL,
				ueBusiness.getUEByCode(1)));
	}

	public ModuleBusiness() {
		uniteEnseignementBusiness = new UniteEnseignementBusiness();
	}

	public List<Module> getAllModules() {
		return modules;
	}

	public Module getModuleByMatricule(String matricule) {
		if (matricule == null)
			return null;
		for (Module module : modules) {
			if (module.getMatricule().equals(matricule))
				return module;
		}
		return null;
	}

	public List<Module> getModulesByType(TypeModule type) {
		List<Module> result = new ArrayList<Module>();
		for (Module module : modules) {
			if (module.getType() == type)
				result.add(module);
		}
		return result;
	}

	public boolean addModule(String matricule, String libelle, int coef, int volumeHoraire,
							 TypeModule type, int codeUE) {
		UniteEnseignement ue = uniteEnseignementBusiness.getUEByCode(codeUE);
		if (matricule == null || matricule.trim().isEmpty() || libelle == null || type == null
				|| ue == null || getModuleByMatricule(matricule) != null)
			return false;
		return modules.add(new Module(matricule, libelle, coef, volumeHoraire, type, ue));
	}

	public boolean updateModule(String matricule, String libelle, int coef, int volumeHoraire,
								TypeModule type, int codeUE) {
		Module existingModule = getModuleByMatricule(matricule);
		UniteEnseignement ue = uniteEnseignementBusiness.getUEByCode(codeUE);
		if (existingModule == null || libelle == null || type == null || ue == null)
			return false;

		existingModule.setNom(libelle);
		existingModule.setCoefficient(coef);
		existingModule.setVolumeHoraire(volumeHoraire);
		existingModule.setType(type);
		existingModule.setUniteEnseignement(ue);
		return true;
	}

	public boolean deleteModule(String matricule) {
		Iterator<Module> iterator = modules.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getMatricule().equals(matricule)) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}
}
