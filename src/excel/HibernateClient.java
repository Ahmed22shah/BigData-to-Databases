package excel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateClient {

	Configuration cfg ;
	SessionFactory factory ;
	Session session;
	public HibernateClient() {
		cfg =new Configuration().configure("hibernate.anno.cfg.xml");
		
		factory = cfg.buildSessionFactory();
		
		session = factory.openSession();
	}
	public void setToSql(int sno, int constituencyNo, String constituencyName, String candidateName, String candidateSex,
			int candidateAge, String candidateCategory, String partyName, int validVotesGeneral,
			int validVotesPostal, int totalValidVotes, int totalElectors, int totalVVPNOTA, double pvotespolled) {

		HibernateDTO dto = HibernateDTO.getHibernateDTO();
		
		dto.setSno(sno);
		dto.setConstituencyNo(constituencyNo);
		dto.setConstituencyName(constituencyName);
		dto.setCandidateName(candidateName);
		dto.setCandidateSex(candidateSex);
		dto.setCandidateAge(candidateAge);
		dto.setCandidateCategory(candidateCategory);
		dto.setPartyName(partyName);
		dto.setValidVotesGeneral(validVotesGeneral);
		dto.setValidVotesPostal(validVotesPostal);
		dto.setTotalValidVotes(totalValidVotes);
		dto.setTotalElectors(totalElectors);
		dto.setTotalVVPNOTA(totalVVPNOTA);
		dto.setPvotespolled(pvotespolled);
		Transaction tx=session.beginTransaction();//		session.beginTransaction().commit();

		session.save(dto);
		
		tx.commit();

	}
	
}
