package ru.chuikov.MuseBackendBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chuikov.MuseBackendBoot.entity.OauthClientDetails;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface OauthClientRepository extends JpaRepository<OauthClientDetails,Long> {

}
