package com.tib.ts.mod.entities;

import java.util.Map;

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

	@JsonProperty("cc:useGuidelines")
	private Map<String, String> useGuidelines;

	@JsonProperty("dcterms:created")
	private Map<String, String> created;

	@JsonProperty("dcterms:dateSubmitted")
	private Map<String, String> dateSubmitted;

	@JsonProperty("dcterms:valid")
	private Map<String, String> valid;

	@JsonProperty("mod:authorProperty")
	private Map<String, String> authorProperty;

	@JsonProperty("mod:averageChildCount")
	private Map<String, String> averageChildCount;

	@JsonProperty("mod:browsingUI")
	private Map<String, String> browsingUI;

	@JsonProperty("mod:classesWithMoreThan25Children")
	private Map<String, String> classesWithMoreThan25Children;

	@JsonProperty("mod:classesWithNoAuthorMetadata")
	private Map<String, String> classesWithNoAuthorMetadata;

	@JsonProperty("mod:classesWithNoDateMetadata")
	private Map<String, String> classesWithNoDateMetadata;

	@JsonProperty("mod:classesWithNoDefinition")
	private Map<String, String> classesWithNoDefinition;

	@JsonProperty("mod:classesWithNoFormalDefinition")
	private Map<String, String> classesWithNoFormalDefinition;

	@JsonProperty("mod:classesWithNoLabel")
	private Map<String, String> classesWithNoLabel;

	@JsonProperty("mod:classesWithOneChild")
	private Map<String, String> classesWithOneChild;

	@JsonProperty("mod:conformsToKnowledgeRepresentationParadigm")
	private Map<String, String> conformsToKnowledgeRepresentationParadigm;

	@JsonProperty("mod:createdProperty")
	private Map<String, String> createdProperty;

	@JsonProperty("mod:curatedOn")
	private Map<String, String> curatedOn;

	@JsonProperty("mod:definitionProperty")
	private Map<String, String> definitionProperty;

	@JsonProperty("mod:fairAssessment")
	private Map<String, String> fairAssessment;

	@JsonProperty("mod:fairScore")
	private Map<String, String> fairScore;

	@JsonProperty("mod:hasRepresentationLanguage")
	private Map<String, String> hasRepresentationLanguage;

	@JsonProperty("mod:hasSyntax")
	private Map<String, String> hasSyntax;

	@JsonProperty("mod:hierarchyProperty")
	private Map<String, String> hierarchyProperty;

	@JsonProperty("mod:maxChildCount")
	private Map<String, String> maxChildCount;

	@JsonProperty("mod:maxDepth")
	private Map<String, String> maxDepth;

	@JsonProperty("mod:metadataVoc")
	private Map<String, String> metadataVoc;

	@JsonProperty("mod:metrics")
	private Map<String, String> metrics;

	@JsonProperty("mod:modifiedProperty")
	private Map<String, String> modifiedProperty;

	@JsonProperty("mod:numberOfAxioms")
	private Map<String, String> numberOfAxioms;

	@JsonProperty("mod:numberOfClasses")
	private Map<String, String> numberOfClasses;

	@JsonProperty("mod:numberOfDataProperties")
	private Map<String, String> numberOfDataProperties;

	@JsonProperty("mod:numberOfDeprecated")
	private Map<String, String> numberOfDeprecated;

	@JsonProperty("mod:numberOfIndividuals")
	private Map<String, String> numberOfIndividuals;

	@JsonProperty("mod:numberOfLabels")
	private Map<String, String> numberOfLabels;

	@JsonProperty("mod:numberOfMappings")
	private Map<String, String> numberOfMappings;

	@JsonProperty("mod:numberOfObjectProperties")
	private Map<String, String> numberOfObjectProperties;

	@JsonProperty("mod:numberOfProperties")
	private Map<String, String> numberOfProperties;

	@JsonProperty("mod:obsoleteParent")
	private Map<String, String> obsoleteParent;

	@JsonProperty("mod:obsoleteProperty")
	private Map<String, String> obsoleteProperty;

	@JsonProperty("mod:prefLabelProperty")
	private Map<String, String> prefLabelProperty;

	@JsonProperty("mod:sampleQueries")
	private Map<String, String> sampleQueries;

	@JsonProperty("mod:synonymProperty")
	private Map<String, String> synonymProperty;

	@JsonProperty("mod:usedEngineeringMethodology")
	private Map<String, String> usedEngineeringMethodology;

	@JsonProperty("owl:deprecated")
	private Map<String, String> deprecated;

	@JsonProperty("owl:imports")
	private Map<String, String> imports;

	@JsonProperty("sd:endpoint")
	private Map<String, String> endpoint;

}
