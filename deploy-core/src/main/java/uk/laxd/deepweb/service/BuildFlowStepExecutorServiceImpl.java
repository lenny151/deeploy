package uk.laxd.deepweb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.laxd.deepweb.executor.BuildFlowStepExecutorFactory;
import uk.laxd.deepweb.model.BuildFlowStep;
import uk.laxd.deepweb.model.BuildFlowStepArgument;
import uk.laxd.deepweb.plugin.ExecutorDefinition;
import uk.laxd.deepweb.plugin.ExecutionResult;
import uk.laxd.deepweb.plugin.PluginDefinition;
import uk.laxd.deepweb.plugin.PluginManager;

import java.util.Collection;
import java.util.Map;

/**
 * Created by lawrence on 02/03/16.
 */
@Service
public class BuildFlowStepExecutorServiceImpl implements BuildFlowStepExecutorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BuildFlowStepExecutorServiceImpl.class);

	@Autowired
	private BuildFlowStepExecutorFactory buildFlowStepExecutorFactory;

	@Autowired
	private BuildFlowStepArgumentService buildFlowStepArgumentService;

	@Autowired
	private PluginManager pluginManager;

	@Override
	public ExecutionResult execute(BuildFlowStep buildFlowStep) {
		Collection<BuildFlowStepArgument> argumentList = buildFlowStep.getArguments();
		Map<String, String> arguments = buildFlowStepArgumentService.createArgumentMap(argumentList);

		PluginDefinition pluginDefinition = pluginManager.getPluginDefinition(buildFlowStep.getExecutorName());

		ExecutorDefinition executorDefinition = buildFlowStepExecutorFactory.createExecutor(pluginDefinition.getExecutor().getName());

		LOGGER.info("Executing Build Flow Step {}", pluginDefinition.getExecutor().getName());

		return executorDefinition.executeWithArguments(arguments);
	}

	@Override
	public Collection<PluginDefinition> getPluginDefinitions() {
		return pluginManager.getPluginDefinitions();
	}
}
