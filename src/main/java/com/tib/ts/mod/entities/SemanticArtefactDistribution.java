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

	@JsonProperty("cc:useGuidelines")
	private List<Map<String, String>> useGuidelines;

	@JsonProperty("dcterms:created")
	private List<Map<String, String>> created;

	@JsonProperty("dcterms:dateSubmitted")
	private List<Map<String, String>> dateSubmitted;

	@JsonProperty("dcterms:valid")
	private List<Map<String, String>> valid;

	@JsonProperty("mod:authorProperty")
	private List<Map<String, String>> authorProperty;

	@JsonProperty("mod:averageChildCount")
	private List<Map<String, String>> averageChildCount;

	@JsonProperty("mod:browsingUI")
	private List<Map<String, String>> browsingUI;

	@JsonProperty("mod:classesWithMoreThan25Children")
	private List<Map<String, String>> classesWithMoreThan25Children;

	@JsonProperty("mod:classesWithNoAuthorMetadata")
	private List<Map<String, String>> classesWithNoAuthorMetadata;

	@JsonProperty("mod:classesWithNoDateMetadata")
	private List<Map<String, String>> classesWithNoDateMetadata;

	@JsonProperty("mod:classesWithNoDefinition")
	private List<Map<String, String>> classesWithNoDefinition;

	@JsonProperty("mod:classesWithNoFormalDefinition")
	private List<Map<String, String>> classesWithNoFormalDefinition;

	@JsonProperty("mod:classesWithNoLabel")
	private List<Map<String, String>> classesWithNoLabel;

	@JsonProperty("mod:classesWithOneChild")
	private List<Map<String, String>> classesWithOneChild;

	@JsonProperty("mod:conformsToKnowledgeRepresentationParadigm")
	private List<Map<String, String>> conformsToKnowledgeRepresentationParadigm;

	@JsonProperty("mod:createdProperty")
	private List<Map<String, String>> createdProperty;

	@JsonProperty("mod:curatedOn")
	private List<Map<String, String>> curatedOn;

	@JsonProperty("mod:definitionProperty")
	private List<Map<String, String>> definitionProperty;

	@JsonProperty("mod:fairAssessment")
	private List<Map<String, String>> fairAssessment;

	@JsonProperty("mod:fairScore")
	private List<Map<String, String>> fairScore;

	@JsonProperty("mod:hasRepresentationLanguage")
	private List<Map<String, String>> hasRepresentationLanguage;

	@JsonProperty("mod:hasSyntax")
	private List<Map<String, String>> hasSyntax;

	@JsonProperty("mod:hierarchyProperty")
	private List<Map<String, String>> hierarchyProperty;

	@JsonProperty("mod:maxChildCount")
	private List<Map<String, String>> maxChildCount;

	@JsonProperty("mod:maxDepth")
	private List<Map<String, String>> maxDepth;

	@JsonProperty("mod:metadataVoc")
	private List<Map<String, String>> metadataVoc;

	@JsonProperty("mod:metrics")
	private List<Map<String, String>> metrics;

	@JsonProperty("mod:modifiedProperty")
	private List<Map<String, String>> modifiedProperty;

	@JsonProperty("mod:numberOfAxioms")
	private List<Map<String, String>> numberOfAxioms;

	@JsonProperty("mod:numberOfClasses")
	private String numberOfClasses;

	@JsonProperty("mod:numberOfDataProperties")
	private String numberOfDataProperties;

	@JsonProperty("mod:numberOfDeprecated")
	private String numberOfDeprecated;

	@JsonProperty("mod:numberOfIndividuals")
	private String numberOfIndividuals;

	@JsonProperty("mod:numberOfLabels")
	private String numberOfLabels;

	@JsonProperty("mod:numberOfMappings")
	private String numberOfMappings;

	@JsonProperty("mod:numberOfObjectProperties")
	private String numberOfObjectProperties;

	@JsonProperty("mod:numberOfProperties")
	private String numberOfProperties;

	@JsonProperty("mod:obsoleteParent")
	private List<Map<String, String>> obsoleteParent;

	@JsonProperty("mod:obsoleteProperty")
	private List<Map<String, String>> obsoleteProperty;

	@JsonProperty("mod:prefLabelProperty")
	private List<Map<String, String>> prefLabelProperty;

	@JsonProperty("mod:sampleQueries")
	private List<Map<String, String>> sampleQueries;

	@JsonProperty("mod:synonymProperty")
	private List<Map<String, String>> synonymProperty;

	@JsonProperty("mod:usedEngineeringMethodology")
	private List<Map<String, String>> usedEngineeringMethodology;

	@JsonProperty("owl:deprecated")
	private List<Map<String, String>> deprecated;

	@JsonProperty("owl:imports")
	private List<Map<String, String>> imports;

	@JsonProperty("sd:endpoint")
	private List<Map<String, String>> endpoint;

	@JsonGetter("@type")
    public String getTypeAsString() {
        return semanticArtefactDistributionType != null ? semanticArtefactDistributionType.toString() : "mod:SemanticArtefactDistribution";
    }
}
