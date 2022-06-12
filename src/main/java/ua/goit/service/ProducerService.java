package ua.goit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.exception.ProducerAlreadyExistsException;
import ua.goit.exception.ProducerNotFoundException;
import ua.goit.model.converter.ProducerConverter;
import ua.goit.model.dto.ProducerDto;
import ua.goit.repository.ProducerRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProducerService {
    private final ProducerRepository producerRepository;
    private final ProducerConverter producerConverter;

    @Autowired
    public ProducerService(ProducerRepository producerRepository, ProducerConverter producerConverter) {
        this.producerRepository = producerRepository;
        this.producerConverter = producerConverter;
    }

    public List<ProducerDto> findAllProducers() {
        return StreamSupport.stream(producerRepository.findAll().spliterator(), false)
                .map(producerConverter::convert)
                .collect(Collectors.toList());
    }

    public ProducerDto findProducerById(UUID id) {
        return producerConverter.convert(producerRepository.findById(id).orElseThrow(() ->
                new ProducerNotFoundException("Producer with id %s not exists", id)));
    }

    public void saveOrUpdate(ProducerDto producerDto) {
        validateToCreateProducer(producerDto);
        producerRepository.save(producerConverter.convert(producerDto));
    }

    public void delete(ProducerDto producerDto) {
        producerRepository.delete(producerConverter.convert(producerDto));
    }

    public void validateToCreateProducer(ProducerDto producerDto) {
        producerRepository.findProducerByName(producerDto.getName())
                .ifPresent((producer) -> {
                    throw new ProducerAlreadyExistsException("Producer with name " + producer.getName() +
                            " already exists");
                });
    }
}
