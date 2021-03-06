package uk.laxd.deepweb.executor;

import uk.laxd.deepweb.executor.lang.InvalidArgumentsException;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by lawrence on 15/04/16.
 */
public class ArgumentValidator {
    public void validate(Collection<ExecutorParameter> parameters, Map<String, String> arguments) {
        List<ExecutorParameter> invalidArguments = parameters.stream()
                .filter(ExecutorParameter::isMandatory)
                .filter(arg -> !arguments.containsKey(arg.getName()))
                .collect(Collectors.toList());

        if(!invalidArguments.isEmpty()) {
            throw new InvalidArgumentsException(invalidArguments);
        }
    }
}
