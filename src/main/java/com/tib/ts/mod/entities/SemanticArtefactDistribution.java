package com.tib.ts.mod.entities;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Getter
@Setter
public class SemanticArtefactDistribution extends Distribution {
	
	@JsonProperty("@context")
	private Map<String, String> context;
	
	@JsonProperty("@id")
	private Object semanticArtefactDistributionId;
	
	@JsonProperty("@type")
	private Object semanticArtefactDistributionType;

	@JsonProperty("useGuidelines")
	private List<Map<String, String>> useGuidelines;

	@JsonProperty("created")
	private List<Map<String, String>> created;

	@JsonProperty("dateSubmitted")
	private List<Map<String, String>> dateSubmitted;

	@JsonProperty("valid")
	private List<Map<String, String>> valid;

	@JsonProperty("authorProperty")
	private List<Map<String, String>> authorProperty;

	@JsonProperty("averageChildCount")
	private List<Map<String, String>> averageChildCount;

	@JsonProperty("browsingUI")
	private List<Map<String, String>> browsingUI;

	@JsonProperty("classesWithMoreThan25Children")
	private List<Map<String, String>> classesWithMoreThan25Children;

	@JsonProperty("classesWithNoAuthorMetadata")
	private List<Map<String, String>> classesWithNoAuthorMetadata;

	@JsonProperty("classesWithNoDateMetadata")
	private List<Map<String, String>> classesWithNoDateMetadata;

	@JsonProperty("classesWithNoDefinition")
	private List<Map<String, String>> classesWithNoDefinition;

	@JsonProperty("classesWithNoFormalDefinition")
	private List<Map<String, String>> classesWithNoFormalDefinition;

	@JsonProperty("classesWithNoLabel")
	private List<Map<String, String>> classesWithNoLabel;

	@JsonProperty("classesWithOneChild")
	private List<Map<String, String>> classesWithOneChild;

	@JsonProperty("conformsToKnowledgeRepresentationParadigm")
	private List<Map<String, String>> conformsToKnowledgeRepresentationParadigm;

	@JsonProperty("createdProperty")
	private List<Map<String, String>> createdProperty;

	@JsonProperty("curatedOn")
	private List<Map<String, String>> curatedOn;

	@JsonProperty("definitionProperty")
	private List<Map<String, String>> definitionProperty;

	@JsonProperty("fairAssessment")
	private List<Map<String, String>> fairAssessment;

	@JsonProperty("fairScore")
	private List<Map<String, String>> fairScore;

	@JsonProperty("hasRepresentationLanguage")
	private List<Map<String, String>> hasRepresentationLanguage;

	@JsonProperty("hasSyntax")
	private List<Map<String, String>> hasSyntax;

	@JsonProperty("hierarchyProperty")
	private List<Map<String, String>> hierarchyProperty;

	@JsonProperty("maxChildCount")
	private List<Map<String, String>> maxChildCount;

	@JsonProperty("maxDepth")
	private List<Map<String, String>> maxDepth;

	@JsonProperty("metadataVoc")
	private List<Map<String, String>> metadataVoc;

	@JsonProperty("metrics")
	private List<Map<String, String>> metrics;

	@JsonProperty("modifiedProperty")
	private List<Map<String, String>> modifiedProperty;

	@JsonProperty("numberOfAxioms")
	private List<Map<String, String>> numberOfAxioms;

	@JsonProperty("numberOfClasses")
	private String numberOfClasses;

	@JsonProperty("numberOfDataProperties")
	private String numberOfDataProperties;

	@JsonProperty("numberOfDeprecated")
	private String numberOfDeprecated;

	@JsonProperty("numberOfIndividuals")
	private String numberOfIndividuals;

	@JsonProperty("numberOfLabels")
	private String numberOfLabels;

	@JsonProperty("numberOfMappings")
	private String numberOfMappings;

	@JsonProperty("numberOfObjectProperties")
	private String numberOfObjectProperties;

	@JsonProperty("numberOfProperties")
	private String numberOfProperties;

	@JsonProperty("obsoleteParent")
	private List<Map<String, String>> obsoleteParent;

	@JsonProperty("obsoleteProperty")
	private List<Map<String, String>> obsoleteProperty;

	@JsonProperty("prefLabelProperty")
	private List<Map<String, String>> prefLabelProperty;

	@JsonProperty("sampleQueries")
	private List<Map<String, String>> sampleQueries;

	@JsonProperty("synonymProperty")
	private List<Map<String, String>> synonymProperty;

	@JsonProperty("usedEngineeringMethodology")
	private List<Map<String, String>> usedEngineeringMethodology;

	@JsonProperty("deprecated")
	private List<Map<String, String>> deprecated;

	@JsonProperty("imports")
	private List<Map<String, String>> imports;

	@JsonProperty("endpoint")
	private List<Map<String, String>> endpoint;

	@JsonGetter("@type")
    public String getTypeAsString() {
        return semanticArtefactDistributionType != null ? semanticArtefactDistributionType.toString() : "mod:SemanticArtefactDistribution";
    }
}
