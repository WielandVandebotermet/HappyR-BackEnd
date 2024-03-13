package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.model.Template;

import java.util.*;

public class TemplateSurvice {
    private List<Template> templateList= new ArrayList<>();

    public TemplateSurvice() {
        templateList.add(new Template(1, "Question Bar",
                new HashMap<String, Boolean>() {{
                    put("subtext", true);
                    put("comment", false);
                }},
                new HashMap<String, String>() {{
                    put("Title", "");
                    put("SubText", "");
                    put("Bmin", "1");
                    put("Bmax", "5");
                    put("Step", "1");
                    put("CategorieId", "");
                }},
                new String[] {}));

        templateList.add(new Template(2, "Team Question Bar",
                new HashMap<String, Boolean>() {{
                    put("subtext", true);
                    put("comment", false);
                    put("IncudeManager", false);
                }},
                new HashMap<String, String>() {{
                    put("Title", "");
                    put("SubText", "");
                    put("Bmin", "1");
                    put("Bmax", "5");
                    put("Step", "1");
                    put("CategorieId", "");
                }},
                new String[] {}));

        templateList.add(new Template(2, "Question Comment",
                new HashMap<String, Boolean>() {{
                    put("subtext", true);
                }},
                new HashMap<String, String>() {{}},
                new String[] {}));
    }

    public List<Template> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<Template> templateList) {
        this.templateList = templateList;
    }

    public Optional<Template> getOptionalTemplateById(int templateId){
        return getTemplateList().stream().filter(t-> t.getId()==templateId).findFirst();
    }
    public Template getTemplateById(Optional<Template> optionalTemplate){
        return optionalTemplate.orElse(null);
    }

    public Template addTemplate(Template newTemplate) {
        newTemplate.setId(templateList.size()+1);
        templateList.add(newTemplate);
        return templateList.get(templateList.size()-1);
    }

    public Template updateUserById(Template updateTemplate, int templateId) {
        Optional<Template> productOptional = getOptionalTemplateById(templateId);
        if (productOptional.isPresent()){
            Template template = productOptional.get();
            template.setTemplateName(updateTemplate.getTemplateName());
            template.setOptions(updateTemplate.getOptions());
            template.setQ(updateTemplate.getQ());
            template.setExternalPeople(updateTemplate.getExternalPeople());
            return template;
        }
        return null;
    }
}
